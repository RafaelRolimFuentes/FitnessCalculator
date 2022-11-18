package com.rafaelfuentes.nutricao.common.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface Dao {

    @Insert
    fun insertRegister(register: Register)

    @Query("SELECT * FROM register WHERE type = :type")
    fun getRegistersBy(type: String): List<Register>
}