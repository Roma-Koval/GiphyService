package com.example.giphyservice.data.room.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDateTime

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun timeToString(time: LocalDateTime): String {
        return time.toString()
    }

    @TypeConverter
    fun stringToTime(string: String): LocalDateTime {
        return LocalDateTime.parse(string)
    }
}