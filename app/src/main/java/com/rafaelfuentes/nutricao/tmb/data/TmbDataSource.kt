package com.rafaelfuentes.nutricao.tmb.data

import com.rafaelfuentes.nutricao.common.model.App
import com.rafaelfuentes.nutricao.common.model.Register
import kotlinx.coroutines.*

class TmbDataSource : DataSource {
    override fun putRegister(result: Double, type: String, callback: TmbCallback) {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = App.db.getDao()
            dao.insertRegister(Register(result = result, responseImc = null, type = type))

            withContext(Dispatchers.Main) {
                callback.onComplete()
            }
        }
    }
}