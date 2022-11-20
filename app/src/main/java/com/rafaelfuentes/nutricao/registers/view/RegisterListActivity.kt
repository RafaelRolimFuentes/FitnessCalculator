package com.rafaelfuentes.nutricao.registers.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.common.base.DependencyInjector
import com.rafaelfuentes.nutricao.common.base.RegisterAdapter
import com.rafaelfuentes.nutricao.common.model.Register
import com.rafaelfuentes.nutricao.common.view.MainActivity
import com.rafaelfuentes.nutricao.databinding.ActivityRegisterListBinding
import com.rafaelfuentes.nutricao.registers.Registers
import com.rafaelfuentes.nutricao.registers.data.RegisterListClick
import com.rafaelfuentes.nutricao.registers.presentation.RegisterPresenter

class RegisterListActivity : AppCompatActivity(), Registers.View, RegisterListClick {
    private var binding: ActivityRegisterListBinding? = null
    private var presenter: Registers.Presenter? = null
    private lateinit var adapter: RegisterAdapter
    private var key: String? = null

    companion object {
        const val KEY = "type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterListBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.registerToolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_keyboard_backspace_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        presenter = RegisterPresenter(this, DependencyInjector.registersRepository())

        key = intent?.extras?.getString(MainActivity.KEY)
        presenter?.getRegistersBy(key)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun showRegisters(responseList: MutableList<Register>) {
        binding?.let {
            it.rvRegister.layoutManager = LinearLayoutManager(this)
            adapter = RegisterAdapter(responseList, key, this)
            it.rvRegister.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun showEmptyList() {
        binding?.let {
            it.rvRegister.layoutManager = LinearLayoutManager(this)
            val emptyList = mutableListOf<Register>()
            adapter = RegisterAdapter(emptyList, KEY, this)
            it.rvRegister.adapter = adapter
            Toast.makeText(this, R.string.empty_list, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showRegisterDeleted() {
        Toast.makeText(this, getString(R.string.deleted), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        presenter = null
    }

    override fun onLongClick(position: Int, register: Register, listUser: MutableList<Register>) {
        AlertDialog.Builder(this)
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
}