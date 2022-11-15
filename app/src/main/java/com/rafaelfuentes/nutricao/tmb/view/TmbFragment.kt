package com.rafaelfuentes.nutricao.tmb.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.databinding.FragmentTmbBinding

class TmbFragment: Fragment(R.layout.fragment_tmb) {
    private var binding: FragmentTmbBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTmbBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}