package com.example.giphyservice

import android.app.Application
import com.example.giphyservice.di.AppComponent
import com.example.giphyservice.di.DaggerAppComponent

// appComponent lives in the Application class to share its lifecycle
class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}