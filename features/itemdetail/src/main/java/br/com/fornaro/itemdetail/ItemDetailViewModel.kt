package br.com.fornaro.itemdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fornaro.domain.model.TodoItem
import br.com.fornaro.domain.usecase.ItemDetailUseCases
import kotlinx.coroutines.launch

class ItemDetailViewModel(
    private val itemDetailUseCases: ItemDetailUseCases
) : ViewModel() {

    private val _error = MutableLiveData<ItemDetailError>()
    val error: LiveData<ItemDetailError>
        get() = _error

    fun save(title: String, description: String) = viewModelScope.launch {
        if (title.isEmpty() && description.isEmpty()) _error.value = TitleAndDescriptionAreEmpty

        val todoItem = TodoItem(
            title = title,
            description = description
        )
        itemDetailUseCases.insert(todoItem)
    }
}

sealed class ItemDetailError
object TitleAndDescriptionAreEmpty : ItemDetailError()