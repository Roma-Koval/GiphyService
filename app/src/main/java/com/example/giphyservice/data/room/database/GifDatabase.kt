package com.example.giphyservice.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.giphyservice.data.room.dao.GifDAO
import com.example.giphyservice.data.room.entities.GifEntity

@Database(entities = [GifEntity::class], version = 1, exportSchema = false)
abstract class GifDatabase : RoomDatabase() {
    abstract fun getGifDao(): GifDAO
}