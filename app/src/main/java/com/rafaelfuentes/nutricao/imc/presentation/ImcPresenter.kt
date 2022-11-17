package com.rafaelfuentes.nutricao.imc.presentation


import androidx.annotation.StringRes
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.imc.Imc


class ImcPresenter(private var view: Imc.View?) : Imc.Presenter {

    override fun calculate(weight: String, height: String) {
        if (validate(weight, height)) {
            val imc = calculateImc(weight.toInt(), height.toInt())
            val response = imcResponse(imc)
            view?.showSuccess(imc, response)
        }
    }


    @StringRes
    private fun imcResponse(imc: Double): Int {
        return when {
            imc < 15 -> R.string.imc_severely_low_weight
            imc < 16 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25 -> R.string.normal
            imc < 30 -> R.string.imc_high_weight
            imc < 35 -> R.string.imc_so_high_weight
            imc < 40 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }

    private fun calculateImc(weight: Int, height: Int): Double {
        return weight / ((height.toDouble() / 100) * (height.toDouble() / 100))

    }

    private fun validate(weight: String, height: String): Boolean {
        val isWeightValid = weight.isNotEmpty() && !weight.startsWith("0")
        val isHeightValid = height.isNotEmpty() && !height.startsWith("0")
        var isTrueOrFalse = true

        if (!isWeightValid) {
            view?.showWeightError(R.string.weight_error)
            isTrueOrFalse = false
        }
        if (!isHeightValid) {
            view?.showHeightError(R.string.height_error)
            isTrueOrFalse = false
        }
        return isTrueOrFalse
    }
}

