package com.example.giphyservice.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "gifs_table")
data class GifEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val gifUrl: String,
        val lastUpdate: LocalDateTime
)
