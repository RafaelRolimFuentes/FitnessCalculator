package com.rafaelfuentes.nutricao.tmb

import androidx.annotation.StringRes

interface Tmb {
    interface Presenter{
        fun calculate(weight: String, height: String, age: String,genre: String, exercise: String)
    }
    interface View{
        fun showSuccess(response: Double)
        fun showWeightError(@StringRes error: Int)
        fun showHeightError(@StringRes error: Int)
        fun showAgeError(@StringRes error: Int)
    }
}