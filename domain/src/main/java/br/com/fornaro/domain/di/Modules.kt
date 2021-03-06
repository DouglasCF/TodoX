package br.com.fornaro.domain.di

import android.app.Application
import androidx.room.Room
import br.com.fornaro.domain.database.AppDatabase
import br.com.fornaro.domain.repository.TodoItemRepository
import br.com.fornaro.domain.usecase.HomeUseCases
import br.com.fornaro.domain.usecase.ItemDetailUseCases
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private val useCasesModules = module {
    single { HomeUseCases(get()) }
    single { ItemDetailUseCases(get()) }
}

private val repositoryModules = module {
    single { TodoItemRepository(get()) }
}

private val databaseModules = module {
    single {
        provideAppDatabase(androidApplication())
    }
    single { get<AppDatabase>().todoItemDao() }
}

private fun provideAppDatabase(application: Application): AppDatabase =
    Room.databaseBuilder(application, AppDatabase::class.java, "database")
        .build()

val modules = listOf(useCasesModules, repositoryModules, databaseModules)