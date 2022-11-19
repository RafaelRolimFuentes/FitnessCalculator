package com.rafaelfuentes.nutricao.imc.data

class ImcRepository(private val dataSource: DataSource) {

    fun putRegister(result: Double, responseImc: Int, type: String, callback: ImcCallback) {
        dataSource.putRegister(result, responseImc, type, callback)
    }
}