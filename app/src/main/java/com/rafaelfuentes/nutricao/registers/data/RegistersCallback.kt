package com.rafaelfuentes.nutricao.registers.data

import com.rafaelfuentes.nutricao.common.model.Register

interface RegistersCallback {
    fun onSuccess(responseList: MutableList<Register>){throw UnsupportedOperationException()}
    fun onFailure(){ throw UnsupportedOperationException() }
    fun onComplete(){ throw UnsupportedOperationException() }

}