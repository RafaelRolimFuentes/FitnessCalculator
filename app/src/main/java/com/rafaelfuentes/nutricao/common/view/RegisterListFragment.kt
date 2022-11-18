package com.rafaelfuentes.nutricao.common.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.common.base.RegisterAdapter
import com.rafaelfuentes.nutricao.common.model.Register
import com.rafaelfuentes.nutricao.databinding.FragmentRegisterBinding

class RegisterListFragment : Fragment(R.layout.fragment_register) {
    private var binding: FragmentRegisterBinding? = null
    private lateinit var adapter: RegisterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        //TODO REMOVER ESSES DADOS FAKES DEPOIS
        val listFake = listOf<Register>()
        adapter = RegisterAdapter(listFake)


        binding?.let {
            it.rvRegister.layoutManager = LinearLayoutManager(requireContext())
            it.rvRegister.adapter = adapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}