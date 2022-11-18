package com.rafaelfuentes.nutricao.registers.data

import com.rafaelfuentes.nutricao.common.base.RegistersCallback

class RegistersRepository(private val dataSource: DataSource) {
    fun getRegistersBy(type: String, callback: RegistersCallback){
        dataSource.getRegistersBy(type, callback)
    }
}