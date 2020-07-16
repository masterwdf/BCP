package com.example.bcp.features.splash

import android.os.Handler
import com.example.domain.interactor.UserUseCase
import com.example.bcp.base.mvp.Presenter
import com.example.bcp.di.PerActivity
import javax.inject.Inject

@PerActivity
class SplashPresenter @Inject
internal constructor(private val userUseCase: UserUseCase) : Presenter<SplashView> {

    private var splashView: SplashView? = null

    override fun attachView(view: SplashView) {
        splashView = view
    }

    override fun resume() {
        // EMPTY
    }

    override fun pause() {
        // EMPTY
    }

    override fun destroy() {
        this.userUseCase.dispose()
        this.splashView = null
    }

    fun verifyVersion() {
        splashView?.showLoading()

        Handler().postDelayed({
            splashView?.hideLoading()
            splashView?.showVerifyVersion()
        }, 3000)
    }
}