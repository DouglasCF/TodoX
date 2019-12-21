package br.com.fornaro.domain

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.fornaro.domain.database.AppDatabase
import br.com.fornaro.domain.database.dao.TodoItemDao
import br.com.fornaro.domain.model.TodoItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TodoItemDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: TodoItemDao

    @Before
    fun before() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        dao = database.todoItemDao()
    }

    @After
    fun after() {
        database.close()
    }

    @Test
    fun testInsert() = runBlockingTest {
        val todoItem = createTodoItem()
        val idInserted = dao.insert(todoItem)
        val todoItemFromDatabase = dao.select(idInserted)
        Assert.assertEquals(idInserted, todoItemFromDatabase.id)
    }

    @Test
    fun testDelete() = runBlockingTest {
        val todoItem = createTodoItem()
        val idInserted = dao.insert(todoItem)
        var todoItemFromDatabase = dao.select(idInserted)
        dao.delete(todoItemFromDatabase)
        todoItemFromDatabase = dao.select(idInserted)
        Assert.assertNull(todoItemFromDatabase)
    }

    @Test
    fun testSelectAll() = runBlockingTest {
        val todoItem = createListTodoItem()
        val idsInserted = dao.insert(todoItem)
        val latch = CountDownLatch(1)
        val todoItems = mutableListOf<TodoItem>()
        val job = launch {
            dao.selectAll().collect {
                todoItems.addAll(it)
                latch.countDown()
            }
        }

        latch.await()
        job.cancel()
        Assert.assertEquals(idsInserted, todoItems.map { it.id })
    }

    private fun createTodoItem() = createListTodoItem()[0]

    private fun createListTodoItem() = listOf(
        TodoItem(
            title = "Title",
            description = "Description"
        ),
        TodoItem(
            title = "Title 2",
            description = "Description 2"
        )
    )
}