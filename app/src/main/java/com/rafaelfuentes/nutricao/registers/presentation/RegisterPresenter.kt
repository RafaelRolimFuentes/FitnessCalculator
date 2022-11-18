package com.rafaelfuentes.nutricao.registers.presentation

import com.rafaelfuentes.nutricao.common.base.RegistersCallback
import com.rafaelfuentes.nutricao.common.model.Register
import com.rafaelfuentes.nutricao.registers.Registers
import com.rafaelfuentes.nutricao.registers.data.RegistersRepository

class RegisterPresenter(private val view: Registers.View, private val repository: RegistersRepository): Registers.Presenter {
    override fun getRegistersBy(type: String?) {
        repository.getRegistersBy(type!!, object : RegistersCallback{

            override fun onSuccess(responseList: List<Register>) {
                view.showRegisters(responseList)
            }
            override fun onFailure(responseList: List<Register>) {
                view.showEmptyList()
            }
        })
    }
}