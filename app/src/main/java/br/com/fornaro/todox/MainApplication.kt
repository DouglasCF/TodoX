package br.com.fornaro.todox

import android.app.Application
import br.com.fornaro.todox.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(allModules)
        }
    }
}