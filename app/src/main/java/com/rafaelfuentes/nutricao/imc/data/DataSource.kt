package com.rafaelfuentes.nutricao.imc.data

import androidx.annotation.StringRes


interface DataSource {
    fun putRegister(result: Double,@StringRes responseImc: Int, type: String, callback: ImcCallback)
}