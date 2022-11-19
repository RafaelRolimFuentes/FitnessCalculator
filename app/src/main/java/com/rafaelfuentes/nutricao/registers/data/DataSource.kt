package com.rafaelfuentes.nutricao.registers.data

import com.rafaelfuentes.nutricao.common.model.Register

interface DataSource {
    fun getRegistersBy(type: String, callback: RegistersCallback)
    fun deleteRegister(register: Register, callback: RegistersCallback)
}