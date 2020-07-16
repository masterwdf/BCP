package com.example.bcp.features.exchange

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.bcp.R
import com.example.bcp.base.BaseActivity
import com.example.bcp.base.mvp.LoadingView
import com.example.bcp.databinding.ActivityExchangeBinding
import com.example.bcp.databinding.ActivitySplashBinding
import com.example.bcp.di.HasComponentJ
import com.example.bcp.features.exchange.di.DaggerExchangeComponent
import com.example.bcp.features.exchange.di.ExchangeComponent
import kotlinx.android.synthetic.main.activity_splash.view.*

class ExchangeActivity : BaseActivity(), HasComponentJ<ExchangeComponent?>, LoadingView {

    private lateinit var binding: ActivityExchangeBinding
    private val fragment = ExchangeFragment()
    private var component: ExchangeComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init(savedInstanceState)
        addFragment(R.id.content_main, fragment)
    }

    override fun init(savedInstanceState: Bundle?) {
        initializeInjector()
    }

    override fun initializeInjector() {
        component = DaggerExchangeComponent.builder()
            .appComponent(getAppComponent())
            .activityModule(getActivityModule())
            .build()
    }

    override fun getComponent(): ExchangeComponent? {
        return component
    }

    override fun showLoading() {
        binding.root.viewLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.root.viewLoading.visibility = View.GONE
    }
}