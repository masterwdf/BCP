package com.example.data.repository.datasource.exchange

import android.content.Context
import com.example.data.entity.MoneyEntity
import io.reactivex.Observable

/**
 * Clase encargada de obtener la data desde el SQLite
 * dicha clase se instancaria una sola vez y se mantendra mientras la aplicacion este viva
 */

internal class ExchangeDBDataStore(private val context: Context) : ExchangeDataStore {

    fun getContext(): Context {
        return context
    }

    override fun getCurrentMoney(): Observable<List<MoneyEntity?>?> {
        return throw UnsupportedOperationException("")
    }

    override fun getMoney(): Observable<List<MoneyEntity?>?> {
        return throw UnsupportedOperationException("")
    }
}
