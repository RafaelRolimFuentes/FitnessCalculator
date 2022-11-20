package com.rafaelfuentes.nutricao.imc.data

import com.rafaelfuentes.nutricao.common.model.App
import com.rafaelfuentes.nutricao.common.model.Register
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImcDataSource : DataSource {

    override fun putRegister(
        result: Double,
        responseImc: Int,
        type: String,
        callback: ImcCallback
    ) {
        CoroutineScope(Dispatchers.IO).launch{
            val dao = App.db.getDao()
            dao.insertRegister(Register(result = result, responseImc = responseImc, type = type))

            withContext(Dispatchers.Main){
                callback.onComplete()
            }
        }
    }
}