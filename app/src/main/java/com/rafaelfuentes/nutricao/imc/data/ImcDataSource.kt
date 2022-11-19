package com.rafaelfuentes.nutricao.imc.data

import android.os.Handler
import android.os.Looper
import com.rafaelfuentes.nutricao.common.model.App
import com.rafaelfuentes.nutricao.common.model.Register
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class ImcDataSource : DataSource {
    private var executor = Executors.newSingleThreadExecutor()
    private var handler = Handler(Looper.getMainLooper())
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