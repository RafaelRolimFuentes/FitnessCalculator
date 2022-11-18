package com.rafaelfuentes.nutricao.tmb.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.rafaelfuentes.nutricao.common.base.FragmentAttachListener
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.databinding.FragmentTmbBinding
import com.rafaelfuentes.nutricao.common.view.CalcFragment
import com.rafaelfuentes.nutricao.common.view.RegisterListFragment
import com.rafaelfuentes.nutricao.tmb.Tmb
import com.rafaelfuentes.nutricao.tmb.presentation.TmbPresenter

class TmbFragment : Fragment(R.layout.fragment_tmb), Tmb.View {
    private var binding: FragmentTmbBinding? = null
    private var presenter: Tmb.Presenter? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTmbBinding.bind(view)

        presenter = TmbPresenter(this)

        binding?.let {
            val stringArrayGenre = resources.getStringArray(R.array.tmb_genre)
            it.autoCompleteGenre.setText(stringArrayGenre.first())
            val adapterGenre = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                stringArrayGenre
            )
            it.autoCompleteGenre.setAdapter(adapterGenre)

            val stringArrayExercise = resources.getStringArray(R.array.tmb_exercises)
            it.autoCompleteExercises.setText(stringArrayExercise.first())
            val adapterExercise = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                stringArrayExercise
            )
            it.autoCompleteExercises.setAdapter(adapterExercise)

            it.tmbEditTextWeight.addTextChangedListener(watcher)
            it.tmbEditTextHeight.addTextChangedListener(watcher)
            it.tmbEditTextAge.addTextChangedListener(watcher)

            it.tmbBtnCalculate.setOnClickListener {
                val weight = binding?.tmbEditTextWeight?.text.toString()
                val height = binding?.tmbEditTextHeight?.text.toString()
                val age = binding?.tmbEditTextAge?.text.toString()
                val genre = binding?.autoCompleteGenre?.text.toString()
                val exercise = binding?.autoCompleteExercises?.text.toString()
                presenter?.calculate(weight, height, age, genre, exercise)
                hideKeyboard()
            }
        }
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
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

    override fun showSuccess(response: Double) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.tmb_title)
            .setMessage(getString(R.string.tmb_formatted, response))
            .setPositiveButton(android.R.string.ok) { a, b -> }
            .setNegativeButton(R.string.save) { a, b ->
                //TODO DELEGAR PRO PRESENTER PARA ELE DELEGAR PARA O REPOSITORY PARA GRAVAR NO BANCO
            }
            .create()
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_calcs) {
            val fragment = CalcFragment().apply {
                arguments = Bundle().apply {
                    putString(CalcFragment.KEY, "tmb")
                }
            }
            fragmentAttachListener?.goToFragmentScreen(fragment)
        }else if(item.itemId == R.id.menu_registers){
            fragmentAttachListener?.goToFragmentScreen(RegisterListFragment())
        }
        return super.onOptionsItemSelected(item)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener)
            fragmentAttachListener = context
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        presenter = null
        fragmentAttachListener = null
    }
}