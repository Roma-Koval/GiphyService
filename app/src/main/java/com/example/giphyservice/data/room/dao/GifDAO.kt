package com.example.giphyservice.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.giphyservice.data.room.entities.GifEntity

@Dao
interface GifDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGif(gifs: List<GifEntity>)

    @Query("SELECT * FROM gifs_table")
    suspend fun getGifsFromDB(): List<GifEntity>
}