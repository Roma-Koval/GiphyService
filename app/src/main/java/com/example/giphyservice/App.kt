package com.example.giphyservice

import android.app.Application
import com.example.giphyservice.di.AppComponent
import com.example.giphyservice.di.AppModule
import com.example.giphyservice.di.DaggerAppComponent
import com.example.giphyservice.di.DatabaseModule

// appComponent lives in the Application class to share its lifecycle
class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(this))
            .appModule(AppModule(this))
            .build()
    }
}