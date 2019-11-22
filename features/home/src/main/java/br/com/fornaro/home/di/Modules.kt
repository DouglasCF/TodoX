package br.com.fornaro.home.di

import br.com.fornaro.home.HomeViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

private val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

val modules = listOf(viewModelModule)