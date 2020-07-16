package com.example.data.mapper

import com.example.data.entity.CurrencyEntity
import com.example.data.entity.MoneyEntity
import com.example.domain.entity.Currency
import com.example.domain.entity.Money
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeEntityDataMapper @Inject
internal constructor() {

    fun transform(input: List<MoneyEntity?>?): List<Money?>? {
        val output = ArrayList<Money>()

        if (null == input) {
            return emptyList()
        }

        for (entity in input) {
            val model = transform(entity)

            if (null != model) {
                output.add(model)
            }
        }

        return output
    }

    fun transform(input: MoneyEntity?): Money? {
        return input?.let {
            Money().apply {
                country = it.country
                symbol = it.symbol
                image = it.image
                currencies = transformCurrency(it.currencies)
            }
        }
    }

    fun transformCurrency(input: List<CurrencyEntity?>?): List<Currency?>? {
        val output = ArrayList<Currency>()

        if (null == input) {
            return emptyList()
        }

        for (entity in input) {
            val model = transformCurrency(entity)

            if (null != model) {
                output.add(model)
            }
        }

        return output
    }

    fun transformCurrency(input: CurrencyEntity?): Currency? {
        return input?.let {
            Currency().apply {
                flag = it.flag
                rate = it.rate
                buy = it.buy
                sell = it.sell
            }
        }
    }
}