package com.rafaelfuentes.nutricao.registers.data

import com.rafaelfuentes.nutricao.common.model.Register

class RegistersRepository(private val dataSource: DataSource) {
    fun getRegistersBy(type: String, callback: RegistersCallback){
        dataSource.getRegistersBy(type, callback)
    }
    fun deleteRegisters(register: Register, callback: RegistersCallback){
        dataSource.deleteRegister(register, callback)
    }
}