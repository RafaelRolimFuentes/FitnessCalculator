package com.rafaelfuentes.nutricao.tmb.data

interface DataSource {
    fun putRegister(result: Double, type: String, callback: TmbCallback)
}