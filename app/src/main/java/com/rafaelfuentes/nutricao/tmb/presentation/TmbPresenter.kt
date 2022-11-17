package com.rafaelfuentes.nutricao.tmb.presentation

import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.tmb.Tmb

class TmbPresenter(private val view: Tmb.View): Tmb.Presenter {
    override fun calculate(weight: String, height: String, age: String, exercise: String) {

        val validate = validate(weight, height, age)

        if(validate){
            view.showSuccess()
        }
    }

    private fun validate(weight: String, height: String, age: String): Boolean{
        val isWeightValid = weight.isNotEmpty() && !weight.startsWith("0")
        val isHeightValid = height.isNotEmpty() && !height.startsWith("0")
        val isAgeValid = age.isNotEmpty() && !age.startsWith("0")
        var isTrueOrFalse = true

        if (!isWeightValid){
            view.showWeightError(R.string.weight_error)
            isTrueOrFalse = false
        }
        if (!isHeightValid){
            view.showHeightError(R.string.height_error)
            isTrueOrFalse = false
        }
        if (!isAgeValid){
            view.showAgeError(R.string.age_error)
            isTrueOrFalse = false
        }
        return isTrueOrFalse

    }
}