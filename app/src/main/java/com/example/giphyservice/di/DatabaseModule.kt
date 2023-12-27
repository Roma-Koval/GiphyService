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
    fun provideGifDB(context: Context): GifDatabase {
        return Room.databaseBuilder(context, GifDatabase::class.java, "GifsDataBase").build()
    }
}