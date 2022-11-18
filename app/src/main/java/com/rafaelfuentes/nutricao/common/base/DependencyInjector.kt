package com.rafaelfuentes.nutricao.common.base

import com.rafaelfuentes.nutricao.common.view.MainActivity
import com.rafaelfuentes.nutricao.imc.data.ImcDataSource
import com.rafaelfuentes.nutricao.imc.data.ImcRepository
import com.rafaelfuentes.nutricao.registers.data.RegistersDataSource
import com.rafaelfuentes.nutricao.registers.data.RegistersRepository

object DependencyInjector {

    fun imcRepository(): ImcRepository{
        return ImcRepository(ImcDataSource())
    }

    fun registersRepository(): RegistersRepository{
        return RegistersRepository(RegistersDataSource())
    }
}