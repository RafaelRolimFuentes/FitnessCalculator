package com.rafaelfuentes.nutricao.common.base

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfuentes.nutricao.R
import com.rafaelfuentes.nutricao.common.model.Register

class RegisterAdapter(private var listUser: List<Register>): RecyclerView.Adapter<RegisterAdapter.RegisterViewHolder>() {

    inner class RegisterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val textView = itemView.findViewById<TextView>(android.R.id.text1)

        fun bind(register: Register){
            textView.text = itemView.context.getString(R.string.response_list, register.imc, register.tmb, register.createdDate)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RegisterViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))

    override fun onBindViewHolder(holder: RegisterViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount() = listUser.size

}