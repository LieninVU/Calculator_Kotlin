package com.example.calculatorapp

import android.app.Application
import com.example.calculatorapp.di.appModule // Убедитесь, что путь к модулю правильный
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule) // Убедитесь, что этот модуль содержит CalculatorViewModel
        }
    }
}