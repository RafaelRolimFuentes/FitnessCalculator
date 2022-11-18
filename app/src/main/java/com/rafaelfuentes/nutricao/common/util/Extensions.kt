package com.rafaelfuentes.nutricao.common.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(): String{
    return SimpleDateFormat("dd/MM/yy HH:mm").format(this)
}