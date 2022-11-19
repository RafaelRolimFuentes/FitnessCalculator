package com.rafaelfuentes.nutricao.registers.data

import com.rafaelfuentes.nutricao.common.model.App
import com.rafaelfuentes.nutricao.common.model.Register
import kotlinx.coroutines.*

class RegistersDataSource : DataSource {

    override fun getRegistersBy(type: String, callback: RegistersCallback) {

        CoroutineScope(Dispatchers.IO).launch {
            val dao = App.db.getDao()
            val responseList = async { dao.getRegistersBy(type) }
            val mutableList = responseList.await()

            if (mutableList.isEmpty()) {
                withContext(Dispatchers.Main) {
                    callback.onFailure()
                }
            } else {
                withContext(Dispatchers.Main) {
                    callback.onSuccess(mutableList)
                }
            }
        }
    }

    override fun deleteRegister(register: Register, callback: RegistersCallback) {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = App.db.getDao()
            dao.deleteRegister(register)

            withContext(Dispatchers.Main) {
                callback.onComplete()
            }
        }
    }
}