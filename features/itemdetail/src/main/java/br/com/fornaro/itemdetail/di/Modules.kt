package br.com.fornaro.itemdetail.di

import br.com.fornaro.itemdetail.ItemDetailViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

private val viewModelModule = module {
    viewModel { ItemDetailViewModel(get()) }
}

val modules = listOf(viewModelModule)