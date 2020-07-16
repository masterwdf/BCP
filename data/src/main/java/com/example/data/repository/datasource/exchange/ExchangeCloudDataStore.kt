package com.example.data.repository.datasource.exchange

import com.example.data.entity.MoneyEntity
import com.example.data.net.service.IExchangeService
import io.reactivex.Observable

/**
 * Clase encargada de obtener los datos desde un servicio
 */

internal class ExchangeCloudDataStore(private val service: IExchangeService) : ExchangeDataStore {

    override fun getCurrentMoney(): Observable<List<MoneyEntity?>?> {
        return service.getCurrentMoney()
    }

    override fun getMoney(): Observable<List<MoneyEntity?>?> {
        return service.getMoney()
    }
}
