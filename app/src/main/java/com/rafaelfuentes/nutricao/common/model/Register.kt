package com.rafaelfuentes.nutricao.common.model

import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Register(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "result") val result: Double,
    @ColumnInfo(name = "response_imc")@StringRes val responseImc: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "created_date") val createdDate: Date = Date()
)
