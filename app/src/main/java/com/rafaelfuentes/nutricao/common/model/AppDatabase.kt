package com.rafaelfuentes.nutricao.common.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rafaelfuentes.nutricao.common.util.DataConverter

@Database(entities = [Register::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getDao(): Dao

    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{

            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database"
                    ).build()

                    return INSTANCE as AppDatabase
                }

            }else return INSTANCE as AppDatabase
        }
    }
}