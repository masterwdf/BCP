package com.example.bcp.features.exchange

import com.example.bcp.base.mvp.Presenter
import com.example.bcp.di.PerActivity
import com.example.domain.entity.Money
import com.example.domain.interactor.BaseObserver
import com.example.domain.interactor.ExchangeUseCase
import javax.inject.Inject

@PerActivity
class ExchangePresenter @Inject
internal constructor(private val exchangeUseCase: ExchangeUseCase) : Presenter<ExchangeView> {

    private var exchangeView: ExchangeView? = null

    override fun attachView(view: ExchangeView) {
        exchangeView = view
    }

    override fun resume() {
        // EMPTY
    }

    override fun pause() {
        // EMPTY
    }

    override fun destroy() {
        this.exchangeUseCase.dispose()
        this.exchangeView = null
    }

    fun getCurrentMoney() {
        exchangeView?.showLoading()
        exchangeUseCase.getCurrentMoney(GetMoneyObserver())
    }

    private inner class GetMoneyObserver : BaseObserver<List<Money?>?>() {

        override fun onNext(t: List<Money?>?) {
            exchangeView?.showMoney(t)
        }

        override fun onComplete() {
            exchangeView?.hideLoading()
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)

            exchangeView?.hideLoading()
            //moneyView?.onError(ErrorFactory.create(exception))
        }
    }
}