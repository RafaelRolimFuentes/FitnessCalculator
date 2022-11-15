package com.rafaelfuentes.nutricao.imc.presentation



import androidx.annotation.StringRes
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.imc.view.Imc


class ImcPresenter(private var view: Imc.View?): Imc.Presenter {

    override fun calculate(weight: String, height: String) {
        val isWeightValid = weight.isNotEmpty() && weight.toInt() > 10
        val isHeightValid = height.isNotEmpty() && height.toInt() > 30

        validate(weight, height)

        if(isWeightValid && isHeightValid){
            val imc = calculateImc(weight, height)
            val response = imcResponse(imc)
            view?.showSuccess(imc,response)
        }
    }
    @StringRes
    private fun imcResponse(imc: Double): Int {
        return when{
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

    private fun calculateImc(weight: String, height: String): Double {
        val weightAsInt = weight.toDouble()
        val heightAsInt = height.toDouble()

        val imc = weightAsInt/(heightAsInt/100 * heightAsInt/100)
        return imc
    }

    private fun validate(weight: String, height: String){
        val isWeightValid = weight.isNotEmpty() && weight.toInt() > 10
        val isHeightValid = height.isNotEmpty() && height.toInt() > 30

        if(!isWeightValid)
            view?.showWeightError(R.string.weight_error)

        if(!isHeightValid)
            view?.showHeightError(R.string.height_error)

    }

}