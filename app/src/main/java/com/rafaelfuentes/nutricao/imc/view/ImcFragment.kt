package com.rafaelfuentes.nutricao.imc.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.databinding.FragmentImcBinding
import com.rafaelfuentes.nutricao.imc.presentation.ImcPresenter

class ImcFragment : Fragment(R.layout.fragment_imc), Imc.View {
    private var binding: FragmentImcBinding? = null
    private var presenter: Imc.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImcBinding.bind(view)
        presenter = ImcPresenter(this)

        binding?.let {
            it.imcEditTextWeight.addTextChangedListener(watcher)
            it.imcEditTextHeight.addTextChangedListener(watcher)

            it.imcBtnCalculate.setOnClickListener {
                val weight = binding?.imcEditTextWeight?.text.toString()
                val height = binding?.imcEditTextHeight?.text.toString()
                presenter?.calculate(weight, height)
                hideKeyboard()
            }
        }
    }
    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding?.let {
                if (it.imcEditTextWeight.isFocused) {
                    it.imcInputLayoutWeight.error = null
                    it.imcEditTextWeight.requestFocus()
                }

                if (it.imcEditTextHeight.isFocused) {
                    it.imcInputLayoutHeight.error = null
                    it.imcEditTextHeight.requestFocus()
                }
            }

        }

        override fun afterTextChanged(s: Editable?) {
        }

    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding?.imcEditTextWeight?.windowToken, 0)
        imm.hideSoftInputFromWindow(binding?.imcEditTextHeight?.windowToken, 0)
    }

    override fun showSuccess(imc: Double, msg: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.imc_formatted, imc))
            .setMessage(getString(msg))
            .setPositiveButton(android.R.string.ok
            ) { dialog, which -> }
            .setNegativeButton(R.string.save){dialog, which ->

            }
            .create()
            .show()
    }

    override fun showWeightError(error: Int) {
        binding?.imcInputLayoutWeight?.error = getString(error)
    }


    override fun showHeightError(error: Int) {
        binding?.imcInputLayoutHeight?.error = getString(error)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        presenter = null
    }
}