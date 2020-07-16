package com.example.domain.repository

import com.example.domain.entity.Money
import io.reactivex.Observable

interface ExchangeRepository {

    fun getCurrentMoney(): Observable<List<Money?>?>

    fun getMoney(): Observable<List<Money?>?>
}