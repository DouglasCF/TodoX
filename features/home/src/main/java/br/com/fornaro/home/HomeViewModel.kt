package br.com.fornaro.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fornaro.domain.model.TodoItem
import br.com.fornaro.domain.usecase.HomeUseCases
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private val _result = MutableLiveData<List<TodoItem>>()
    val result: LiveData<List<TodoItem>>
        get() = _result

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    init {
        loadTodoItems()
    }

    fun loadTodoItems() = viewModelScope.launch {
        _progress.value = true
        _error.value = false
        try {
            homeUseCases.getTodoItems().collect {
                _progress.value = false
                _result.value = it
            }
        } catch (e: Exception) {
            _error.value = true
        } finally {
            _progress.value = false
        }
    }
}
