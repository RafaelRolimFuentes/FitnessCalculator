package com.rafaelfuentes.nutricao.registers.data

import com.rafaelfuentes.nutricao.common.model.Register

interface RegisterListClick {
    fun onLongClick(position: Int, register: Register, listUser: MutableList<Register>)
}