package com.rafaelfuentes.nutricao.common.model

import java.util.*

data class Register(
    val imc: Double,
    val tmb: Double,
    val createdDate: Date = Date()
)
