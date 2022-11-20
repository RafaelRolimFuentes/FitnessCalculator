package com.rafaelfuentes.nutricao.common.base

import androidx.fragment.app.Fragment

interface FragmentAttachListener {
    fun goToFragmentScreen(fragment: Fragment)
    fun goToRegisterListActivity(key: String)
    fun goToCalcActivity(key: String)

}