package com.example.data.repository

import com.example.data.mapper.ExchangeEntityDataMapper
import com.example.data.repository.datasource.exchange.ExchangeDataStoreFactory
import com.example.domain.entity.Money
import com.example.domain.repository.ExchangeRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeDataRepository @Inject internal constructor(
    private val exchangeDataStoreFactory: ExchangeDataStoreFactory,
    private val exchangeEntityDataMapper: ExchangeEntityDataMapper
) : ExchangeRepository {

    override fun getCurrentMoney(): Observable<List<Money?>?> {
        val dataStore = exchangeDataStoreFactory.createCloud()
        return dataStore.getCurrentMoney().map { exchangeEntityDataMapper.transform(it) }
    }

    override fun getMoney(): Observable<List<Money?>?> {
        val dataStore = exchangeDataStoreFactory.createCloud()
        return dataStore.getMoney().map { exchangeEntityDataMapper.transform(it) }
    }
}