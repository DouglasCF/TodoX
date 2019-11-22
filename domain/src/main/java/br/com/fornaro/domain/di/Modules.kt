package br.com.fornaro.domain.di

import br.com.fornaro.domain.usecase.HomeUseCases
import org.koin.dsl.module

private val useCasesModules = module {
    single { HomeUseCases() }
}

val modules = listOf(useCasesModules)