package com.rafaelfuentes.nutricao.registers.view

import android.app.AlertDialog
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
import com.rafaelfuentes.nutricao.registers.data.RegisterListClick
import com.rafaelfuentes.nutricao.registers.presentation.RegisterPresenter

class RegisterListFragment : Fragment(R.layout.fragment_register), Registers.View,
    RegisterListClick {
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

    override fun showRegisters(responseList: MutableList<Register>) {
        binding?.let {
            it.rvRegister.layoutManager = LinearLayoutManager(requireContext())
            adapter = RegisterAdapter(responseList, key, this)
            it.rvRegister.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun showEmptyList() {
        binding?.let {
            it.rvRegister.layoutManager = LinearLayoutManager(requireContext())
            val emptyList = mutableListOf<Register>()
            adapter = RegisterAdapter(emptyList, KEY, this)
            it.rvRegister.adapter = adapter
            Toast.makeText(requireContext(), R.string.empty_list, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onLongClick(position: Int, register: Register, listUser: MutableList<Register>) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.really_dalete)
            .setPositiveButton(R.string.no) { dialog, which -> }
            .setNegativeButton(R.string.delete) { dialog, which ->
                presenter?.deleteRegister(register)
                listUser.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
            .create()
            .show()
    }

    override fun showRegisterDeleted() {
        Toast.makeText(requireContext(), getString(R.string.deleted), Toast.LENGTH_SHORT).show()
    }
}