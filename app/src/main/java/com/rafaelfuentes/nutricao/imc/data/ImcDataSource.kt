package com.rafaelfuentes.nutricao.imc.data


import android.os.Handler
import android.os.Looper
import com.rafaelfuentes.nutricao.common.model.App
import com.rafaelfuentes.nutricao.common.model.Register
import java.util.concurrent.Executors

class ImcDataSource: DataSource {
    private var executor = Executors.newSingleThreadExecutor()
    private var handler = Handler(Looper.getMainLooper())
    override fun putRegister(
        result: Double,
        responseImc: Int,
        type: String,
        callback: ImcCallback
    ) {
        executor.execute {
            val dao = App.db.getDao()
            dao.insertRegister(Register(result = result, responseImc = responseImc, type = type))

            handler.post {
                callback.onComplete()
            }
        }
    }
}