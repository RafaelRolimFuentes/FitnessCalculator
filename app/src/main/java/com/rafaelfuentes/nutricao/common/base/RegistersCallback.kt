package com.rafaelfuentes.nutricao.common.base

import com.rafaelfuentes.nutricao.common.model.Register

interface RegistersCallback {
    fun onSuccess(responseList: List<Register>)
    fun onFailure(responseList: List<Register>)
}