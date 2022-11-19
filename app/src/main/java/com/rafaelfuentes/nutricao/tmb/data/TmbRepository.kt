package com.rafaelfuentes.nutricao.tmb.data

class TmbRepository(private val dataSource: DataSource) {

    fun putRegister(result: Double, type: String, callback: TmbCallback) {
        dataSource.putRegister(result, type, callback)
    }
}