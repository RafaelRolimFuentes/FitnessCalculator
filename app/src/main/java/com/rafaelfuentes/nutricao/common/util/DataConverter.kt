package com.rafaelfuentes.nutricao.common.util

import androidx.room.TypeConverter
import java.util.*

class DataConverter {

    @TypeConverter
    fun toDate(dateLong: Long?): Date?{
        return if(dateLong!= null) Date(dateLong) else null
    }

    @TypeConverter
    fun fromDate(date: Date?): Long?{
        return date?.time
    }
}