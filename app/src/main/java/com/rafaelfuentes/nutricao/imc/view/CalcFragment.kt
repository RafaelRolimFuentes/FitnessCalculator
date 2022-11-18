package com.rafaelfuentes.nutricao.imc.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.databinding.FragmentCalcBinding

class CalcFragment : Fragment(R.layout.fragment_calc) {
    private var binding: FragmentCalcBinding? = null

    companion object {
        const val KEY = "type"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalcBinding.bind(view)

        val key = arguments?.getString(KEY)

        key?.let {
            if (it.equals("imc")) {
                binding?.let {
                    it.imcContainer.visibility = View.VISIBLE
                    it.tmbMaleContainer.visibility = View.GONE
                    it.tmbFemaleContainer.visibility = View.GONE
                }
            }
            if (it.equals("tmb")) {
                binding?.let {
                    it.imcContainer.visibility = View.GONE
                    it.tmbMaleContainer.visibility = View.VISIBLE
                    it.tmbFemaleContainer.visibility = View.VISIBLE
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}