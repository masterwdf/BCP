package com.example.data.repository.datasource.exchange

import com.example.data.entity.MoneyEntity
import io.reactivex.Observable

interface ExchangeDataStore {

    fun getCurrentMoney(): Observable<List<MoneyEntity?>?>

    fun getMoney(): Observable<List<MoneyEntity?>?>
}
