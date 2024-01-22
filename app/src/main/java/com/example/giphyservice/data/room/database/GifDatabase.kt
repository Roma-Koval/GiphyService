package com.example.giphyservice.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.giphyservice.data.room.converters.Converters
import com.example.giphyservice.data.room.dao.GifDAO
import com.example.giphyservice.data.room.entities.GifEntity

@Database(entities = [GifEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GifDatabase : RoomDatabase() {
    abstract fun getGifDao(): GifDAO
}