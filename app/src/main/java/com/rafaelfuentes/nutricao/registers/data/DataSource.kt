package com.rafaelfuentes.nutricao.registers.data

import com.rafaelfuentes.nutricao.common.base.RegistersCallback

interface DataSource {
    fun getRegistersBy(type: String, callback: RegistersCallback)
}