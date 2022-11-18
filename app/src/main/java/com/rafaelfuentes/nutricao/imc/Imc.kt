package com.rafaelfuentes.nutricao.imc

import androidx.annotation.StringRes

interface Imc {
    interface Presenter{
        fun calculate(weight: String, height: String)
        fun putRegister(result: Double,@StringRes responseImc: Int, type: String)
    }
    interface View{
        fun showDialog(imc: Double, @StringRes responseImc: Int)
        fun showRegisterList(type: String)
        fun showWeightError(@StringRes error: Int)
        fun showHeightError(@StringRes error: Int)
    }
}