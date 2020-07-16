package com.example.bcp.features.exchange

import com.example.bcp.base.mvp.LoadingView
import com.example.bcp.base.mvp.View
import com.example.domain.entity.Money

interface ExchangeView : View, LoadingView {

    fun showMoney(list: List<Money?>?)
}