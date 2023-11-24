package com.example.giphyservice.di

import com.example.giphyservice.ui.main.MainFragment
import dagger.Component

@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    //Dagger check the MainFragment for the fields inside to provide values
    fun inject(mainFragment: MainFragment)
}