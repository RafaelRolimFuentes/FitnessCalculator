package com.rafaelfuentes.nutricao.tmb.data

import com.rafaelfuentes.nutricao.imc.data.ImcCallback

interface DataSource {
    fun putRegister(result: Double, type: String, callback: TmbCallback)
}