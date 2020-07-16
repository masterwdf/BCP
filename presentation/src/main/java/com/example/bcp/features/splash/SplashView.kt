package com.example.bcp.features.splash

import com.example.bcp.base.mvp.LoadingView
import com.example.bcp.base.mvp.View

interface SplashView : View, LoadingView {

    fun showVerifyVersion()

}