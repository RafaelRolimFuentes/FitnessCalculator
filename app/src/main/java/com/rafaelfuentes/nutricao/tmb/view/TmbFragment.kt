package com.rafaelfuentes.nutricao.tmb.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.databinding.FragmentTmbBinding
import com.rafaelfuentes.nutricao.tmb.Tmb
import com.rafaelfuentes.nutricao.tmb.presentation.TmbPresenter

class TmbFragment: Fragment(R.layout.fragment_tmb), Tmb.View {
    private var binding: FragmentTmbBinding? = null
    private var presenter: Tmb.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTmbBinding.bind(view)

        presenter = TmbPresenter(this)

        binding?.let {
            val stringArray = resources.getStringArray(R.array.tmb_exercises)
            it.autoCompleteExercises.setText(stringArray.first())
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, stringArray)
            it.autoCompleteExercises.setAdapter(adapter)

            it.tmbEditTextWeight.addTextChangedListener(watcher)
            it.tmbEditTextHeight.addTextChangedListener(watcher)
            it.tmbEditTextAge.addTextChangedListener(watcher)

            it.tmbBtnCalculate.setOnClickListener {
                val weight = binding?.tmbEditTextWeight?.text.toString()
                val height = binding?.tmbEditTextHeight?.text.toString()
                val age = binding?.tmbEditTextAge?.text.toString()
                val spinnerText = binding?.autoCompleteExercises?.text.toString()
                presenter?.calculate(weight, height, age, spinnerText)
                hideKeyboard()
            }
        }
    }
    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding?.let {
                if (it.tmbEditTextWeight.isFocused) {
                    it.tmbInputLayoutWeight.error = null
                    it.tmbInputLayoutWeight.requestFocus()
                }

                if (it.tmbEditTextHeight.isFocused) {
                    it.tmbInputLayoutHeight.error = null
                    it.tmbInputLayoutHeight.requestFocus()
                }

                if (it.tmbEditTextAge.isFocused) {
                    it.tmbInputLayoutAge.error = null
                    it.tmbInputLayoutAge.requestFocus()
                }
            }

        }

        override fun afterTextChanged(s: Editable?) {
        }

    }

    override fun showSuccess() {
        Toast.makeText(requireContext(), "Deu", Toast.LENGTH_LONG).show()
    }

    override fun showWeightError(error: Int) {
        binding?.tmbInputLayoutWeight?.error = getString(error)

    }

    override fun showHeightError(error: Int) {
        binding?.tmbInputLayoutHeight?.error = getString(error)

    }

    override fun showAgeError(error: Int) {
        binding?.tmbInputLayoutAge?.error = getString(error)

    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding?.tmbEditTextWeight?.windowToken, 0)
        imm.hideSoftInputFromWindow(binding?.tmbEditTextHeight?.windowToken, 0)
        imm.hideSoftInputFromWindow(binding?.tmbEditTextAge?.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        presenter = null
    }
}