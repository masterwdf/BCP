package com.example.bcp.features.money

import com.example.bcp.base.mvp.LoadingView
import com.example.bcp.base.mvp.View
import com.example.domain.entity.Money

interface MoneyView : View, LoadingView {

    fun showMoney(list: List<Money?>?)

}