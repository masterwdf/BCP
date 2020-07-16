package com.example.bcp.features.splash

import android.os.Bundle
import android.view.View
import com.example.bcp.R
import com.example.bcp.base.BaseActivity
import com.example.bcp.base.mvp.LoadingView
import com.example.bcp.databinding.ActivitySplashBinding
import com.example.bcp.di.HasComponentJ
import com.example.bcp.features.splash.di.DaggerSplashComponent
import com.example.bcp.features.splash.di.SplashComponent
import kotlinx.android.synthetic.main.activity_splash.view.*

class SplashActivity : BaseActivity(), HasComponentJ<SplashComponent?>, LoadingView {

    private lateinit var binding: ActivitySplashBinding
    private val fragment = SplashFragment()
    private var component: SplashComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init(savedInstanceState)
        addFragment(R.id.content_main, fragment)
    }

    override fun init(savedInstanceState: Bundle?) {
        initializeInjector()
    }

    override fun initializeInjector() {
        component = DaggerSplashComponent.builder()
            .appComponent(getAppComponent())
            .activityModule(getActivityModule())
            .build()
    }

    override fun getComponent(): SplashComponent? {
        return component
    }

    override fun showLoading() {
        binding.root.viewLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.root.viewLoading.visibility = View.GONE
    }
}