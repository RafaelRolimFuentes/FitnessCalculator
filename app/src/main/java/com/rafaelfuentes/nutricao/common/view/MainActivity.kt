package com.rafaelfuentes.nutricao.common.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.rafaelfuentes.nutricao.common.base.FragmentAttachListener
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.databinding.ActivityMainBinding
import com.rafaelfuentes.nutricao.registers.view.RegisterListActivity

class MainActivity : AppCompatActivity(), FragmentAttachListener {
    private var binding: ActivityMainBinding? = null

    companion object{
       const val KEY = "key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)
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

    override fun goToRegisterListActivity(key: String) {
        val intent = Intent(this, RegisterListActivity::class.java)
        intent.putExtra(KEY, key)
        startActivity(intent)
    }

    override fun goToCalcActivity(key: String) {
        val intent = Intent(this, CalcActivity::class.java)
        intent.putExtra(KEY, key)
        startActivity(intent)
    }
}