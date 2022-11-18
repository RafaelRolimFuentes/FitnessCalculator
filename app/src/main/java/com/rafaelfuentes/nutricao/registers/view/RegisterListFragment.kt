package com.rafaelfuentes.nutricao.registers.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.common.base.DependencyInjector
import com.rafaelfuentes.nutricao.common.base.FragmentAttachListener
import com.rafaelfuentes.nutricao.common.base.RegisterAdapter
import com.rafaelfuentes.nutricao.common.model.Register
import com.rafaelfuentes.nutricao.databinding.FragmentRegisterBinding
import com.rafaelfuentes.nutricao.registers.Registers
import com.rafaelfuentes.nutricao.registers.presentation.RegisterPresenter

class RegisterListFragment : Fragment(R.layout.fragment_register), Registers.View {
    private var binding: FragmentRegisterBinding? = null
    private var presenter: Registers.Presenter? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    private lateinit var adapter: RegisterAdapter
    private var key: String? = null

    companion object {
        const val KEY = "type"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
        presenter = RegisterPresenter(this, DependencyInjector.registersRepository())

        key = arguments?.getString(KEY)
        presenter?.getRegistersBy(key)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener)
            fragmentAttachListener = context
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        fragmentAttachListener = null
    }

    override fun showRegisters(responseList: List<Register>) {
        binding?.let {
            it.rvRegister.layoutManager = LinearLayoutManager(requireContext())
            adapter = RegisterAdapter(responseList, key)
            it.rvRegister.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun showEmptyList() {
        binding?.let {
            it.rvRegister.layoutManager = LinearLayoutManager(requireContext())
            adapter = RegisterAdapter(emptyList(), KEY)
            it.rvRegister.adapter = adapter
            Toast.makeText(requireContext(), R.string.empty_list, Toast.LENGTH_SHORT).show()
        }
    }
}