package com.rafaelfuentes.nutricao.tmb.data

import android.os.Handler
import android.os.Looper
import com.rafaelfuentes.nutricao.common.model.App
import com.rafaelfuentes.nutricao.common.model.Register
import java.util.concurrent.Executors

class TmbDataSource : DataSource {
    private var executor = Executors.newSingleThreadExecutor()
    private var handler = Handler(Looper.getMainLooper())
    override fun putRegister(result: Double, type: String, callback: TmbCallback) {

        executor.execute {
            val dao = App.db.getDao()
            dao.insertRegister(Register(result = result, responseImc = null, type = type))

            handler.post {
                callback.onComplete()
            }
        }
    }
}