package com.rafaelfuentes.nutricao

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rafaelfuentes.nutricao.databinding.FragmentHomeBinding
import com.rafaelfuentes.nutricao.imc.view.ImcFragment
import com.rafaelfuentes.nutricao.tmb.view.TmbFragment

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var binding: FragmentHomeBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding?.let {
            it.btnImc.setOnClickListener {
                fragmentAttachListener?.goToFragmentScreen(ImcFragment())
            }
            it.btnTmb.setOnClickListener {
                fragmentAttachListener?.goToFragmentScreen(TmbFragment())
            }
        }
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
}