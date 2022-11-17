package com.rafaelfuentes.nutricao

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rafaelfuentes.nutricao.databinding.ActivityMainBinding
import com.rafaelfuentes.nutricao.imc.view.ImcFragment

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