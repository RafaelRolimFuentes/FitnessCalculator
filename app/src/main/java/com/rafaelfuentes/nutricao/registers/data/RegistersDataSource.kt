package com.rafaelfuentes.nutricao.registers.data

import android.os.Handler
import android.os.Looper
import com.rafaelfuentes.nutricao.common.base.RegistersCallback
import com.rafaelfuentes.nutricao.common.model.App
import java.util.concurrent.Executors

class RegistersDataSource : DataSource {
    private var executor = Executors.newSingleThreadExecutor()
    private var handler = Handler(Looper.getMainLooper())

    override fun getRegistersBy(type: String, callback: RegistersCallback) {
        executor.execute {

            val dao = App.db.getDao()
            val responseList = dao.getRegistersBy(type)

            if (responseList.isEmpty()) {
                handler.post {
                    callback.onFailure(responseList)
                }
            } else {
                handler.post {
                    callback.onSuccess(responseList)
                }
            }
        }
    }
}