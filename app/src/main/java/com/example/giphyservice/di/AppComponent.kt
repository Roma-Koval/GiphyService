package com.example.giphyservice.di

import com.example.giphyservice.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {
    //Dagger check the MainFragment for the fields inside to provide values
    fun inject(mainFragment: MainFragment)
}