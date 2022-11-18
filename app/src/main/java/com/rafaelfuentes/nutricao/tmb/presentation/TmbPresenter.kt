package com.rafaelfuentes.nutricao.tmb.presentation

import android.util.Log
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.tmb.Tmb

class TmbPresenter(private val view: Tmb.View) : Tmb.Presenter {
    override fun calculate(
        weight: String,
        height: String,
        age: String,
        genre: String,
        exercise: String
    ) {

        val validate = validate(weight, height, age)

        if (validate) {
            val tmb = calculateTmb(weight, height, age, genre)
            val response = tmbResponse(tmb, exercise)
            view.showSuccess(response)
        }
    }

    private fun tmbResponse(tmb: Double, exercise: String): Double {

        return when{
            exercise.equals("Pouco ou nenhum exercício")->{tmb * 1.2}
            exercise.equals("Exercício leve. 1 a 3 dia(s) por semana")->{tmb * 1.375}
            exercise.equals("Exercício moderado, 3 a 5 dias por semana")->{tmb * 1.55}
            exercise.equals("Exercício pesado. 6 a 7 dias por semana")->{tmb * 1.725}
            exercise.equals("Exercício pesado diariamente e até 2x por dia")->{tmb * 1.9}
            else -> 0.0
        }
    }

    private fun validate(weight: String, height: String, age: String): Boolean {
        val isWeightValid = weight.isNotEmpty() && !weight.startsWith("0")
        val isHeightValid = height.isNotEmpty() && !height.startsWith("0")
        val isAgeValid = age.isNotEmpty() && !age.startsWith("0")
        var isTrueOrFalse = true

        if (!isWeightValid) {
            view.showWeightError(R.string.weight_error)
            isTrueOrFalse = false
        }
        if (!isHeightValid) {
            view.showHeightError(R.string.height_error)
            isTrueOrFalse = false
        }
        if (!isAgeValid) {
            view.showAgeError(R.string.age_error)
            isTrueOrFalse = false
        }
        return isTrueOrFalse

    }

    private fun calculateTmb(
        weight: String,
        height: String,
        age: String,
        genre: String
    ): Double {

        return if (genre.equals("Mulher")) {
            val mulher =
                655 + ((weight.toInt() * 9.6) + ( height.toInt()  * 1.8) - (age.toInt() * 4.7))
            mulher
        } else {
            val homem =
                66 + ((weight.toInt()  * 13.8) + (height.toInt()  * 5.0) - (age.toInt() * 6.8))
            homem
        }

    }
}