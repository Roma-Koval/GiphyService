package com.example.giphyservice.di

import android.content.Context
import com.example.giphyservice.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class, RepositoryModule::class, DatabaseModule::class])
interface AppComponent {
    //Dagger check the MainFragment for the fields inside to provide values
    fun inject(mainFragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}