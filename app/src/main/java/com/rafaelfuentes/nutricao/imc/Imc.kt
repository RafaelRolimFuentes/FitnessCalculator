package com.rafaelfuentes.nutricao.imc

import androidx.annotation.StringRes

interface Imc {

    interface Presenter{
        fun calculate(weight: String, height: String)
    }
    interface View{
        fun showSuccess(imc: Double,@StringRes msg: Int)
        fun showWeightError(@StringRes error: Int)
        fun showHeightError(@StringRes error: Int)
    }
}