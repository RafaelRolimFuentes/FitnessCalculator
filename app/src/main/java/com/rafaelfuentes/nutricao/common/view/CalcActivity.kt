package com.rafaelfuentes.nutricao.common.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rafaelfuentes.nutricao.databinding.ActivityCalcBinding

class CalcActivity : AppCompatActivity() {
    private var binding: ActivityCalcBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val key = intent?.extras?.getString(MainActivity.KEY)
        key?.let {
            if (it.equals("imc")) {
                binding?.let {
                    it.imcContainer.visibility = View.VISIBLE
                    it.tmbMaleContainer.visibility = View.GONE
                    it.tmbFemaleContainer.visibility = View.GONE
                }
            }
            if (it.equals("tmb")) {
                binding?.let {
                    it.imcContainer.visibility = View.GONE
                    it.tmbMaleContainer.visibility = View.VISIBLE
                    it.tmbFemaleContainer.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}