package com.rafaelfuentes.nutricao.registers

import com.rafaelfuentes.nutricao.common.model.Register

interface Registers {
    interface Presenter{
        fun getRegistersBy(type: String?)
        fun deleteRegister(register: Register)
    }

    interface View{
        fun showRegisters(responseList: MutableList<Register>)
        fun showEmptyList()
        fun showRegisterDeleted()
    }
}