package com.rafaelfuentes.nutricao.common.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface Dao {

    @Insert
    fun insertRegister(register: Register)

    @Query("SELECT * FROM register WHERE type = :type")
    fun getRegistersBy(type: String): MutableList<Register>

    @Delete
    fun deleteRegister(register: Register)
}