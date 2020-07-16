package com.example.data.net.service

import com.example.data.entity.MoneyEntity
import io.reactivex.Observable

interface IExchangeService {

    //@Headers(Constant.TRANSFORM)
    //@POST(value = "money")
    //@Body
    fun getCurrentMoney(): Observable<List<MoneyEntity?>?>

    fun getMoney(): Observable<List<MoneyEntity?>?>
}