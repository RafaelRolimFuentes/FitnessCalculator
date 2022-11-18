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
import com.rafaelfuentes.nutricao.common.util.format

class RegisterAdapter(private var listUser: List<Register>, private val type: String?): RecyclerView.Adapter<RegisterAdapter.RegisterViewHolder>() {

    inner class RegisterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val textView = itemView.findViewById<TextView>(android.R.id.text1)

        fun bind(register: Register){

            val responseImc = itemView.context.getString(register.responseImc)

            if(type == "imc"){
                textView.text = itemView.context.getString(R.string.response_imc_list, register.result, responseImc, register.createdDate.format())

            }else if(type == "tmb"){
                textView.text = itemView.context.getString(R.string.response_tmb_list, register.result, register.createdDate)

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RegisterViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))

    override fun onBindViewHolder(holder: RegisterViewHolder, position: Int) {
        holder.bind(listUser[position])

    }

    override fun getItemCount() = listUser.size


}