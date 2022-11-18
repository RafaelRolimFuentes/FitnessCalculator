package com.rafaelfuentes.nutricao.registers

import com.rafaelfuentes.nutricao.common.model.Register

interface Registers {
    interface Presenter{
        fun getRegistersBy(type: String?)
    }

    interface View{
        fun showRegisters(responseList: List<Register>)
        fun showEmptyList()
    }
}