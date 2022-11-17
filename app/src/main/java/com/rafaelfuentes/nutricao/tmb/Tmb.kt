package com.rafaelfuentes.nutricao.tmb

import androidx.annotation.StringRes

interface Tmb {

    interface Presenter{
        fun calculate(weight: String, height: String, age: String, exercise: String)

    }

    interface View{
        fun showSuccess()
        fun showWeightError(@StringRes error: Int)
        fun showHeightError(@StringRes error: Int)
        fun showAgeError(@StringRes error: Int)
    }
}