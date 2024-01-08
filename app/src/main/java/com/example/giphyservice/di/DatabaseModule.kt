package com.example.giphyservice.di

import android.content.Context
import androidx.room.Room
import com.example.giphyservice.data.room.database.GifDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideGifDB(
        context: Context
    ): GifDatabase = Room.databaseBuilder(
        context = context,
        klass = GifDatabase::class.java,
        name = "GifsDataBase"
    ).build()
}