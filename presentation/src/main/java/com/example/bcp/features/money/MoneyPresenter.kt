package com.example.bcp.features.money

import com.example.bcp.base.mvp.Presenter
import com.example.bcp.di.PerActivity
import com.example.domain.entity.Money
import com.example.domain.interactor.BaseObserver
import com.example.domain.interactor.ExchangeUseCase
import javax.inject.Inject

@PerActivity
class MoneyPresenter @Inject
internal constructor(private val exchangeUseCase: ExchangeUseCase) : Presenter<MoneyView> {

    private var moneyView: MoneyView? = null

    override fun attachView(view: MoneyView) {
        moneyView = view
    }

    override fun resume() {
        // EMPTY
    }

    override fun pause() {
        // EMPTY
    }

    override fun destroy() {
        exchangeUseCase.dispose()
        moneyView = null
    }

    fun getMoney() {
        moneyView?.showLoading()
        exchangeUseCase.getMoney(GetMoneyObserver())
    }

    private inner class GetMoneyObserver : BaseObserver<List<Money?>?>() {

        override fun onNext(t: List<Money?>?) {
            moneyView?.showMoney(t)
        }

        override fun onComplete() {
            moneyView?.hideLoading()
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)

            moneyView?.hideLoading()
            //moneyView?.onError(ErrorFactory.create(exception))
        }
    }
}