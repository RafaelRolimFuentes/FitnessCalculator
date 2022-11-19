package com.rafaelfuentes.nutricao.common.model

import android.app.Application

class App: Application() {

    companion object{
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDatabase(this)
    }
}