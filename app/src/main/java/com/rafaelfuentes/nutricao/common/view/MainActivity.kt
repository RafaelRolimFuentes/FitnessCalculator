package com.rafaelfuentes.nutricao.common.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.rafaelfuentes.nutricao.common.base.FragmentAttachListener
import com.rafaelfuentes.nutricao.R

class MainActivity : AppCompatActivity(), FragmentAttachListener {
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        val fragment = HomeFragment()
        goToFragmentScreen(fragment)

    }
    
    override fun goToFragmentScreen(fragment: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.main_fragment) == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_fragment, fragment)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.main_fragment, fragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}