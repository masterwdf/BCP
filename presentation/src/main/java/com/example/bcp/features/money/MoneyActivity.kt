package com.example.bcp.features.money

import android.os.Bundle
import android.view.View
import com.example.bcp.R
import com.example.bcp.base.BaseActivity
import com.example.bcp.base.mvp.LoadingView
import com.example.bcp.databinding.ActivityMoneyBinding
import com.example.bcp.di.HasComponentJ
import com.example.bcp.features.money.di.DaggerMoneyComponent
import com.example.bcp.features.money.di.MoneyComponent
import kotlinx.android.synthetic.main.activity_splash.view.*

class MoneyActivity : BaseActivity(), HasComponentJ<MoneyComponent?>, LoadingView {

    private lateinit var binding: ActivityMoneyBinding
    private val fragment = MoneyFragment()
    private var component: MoneyComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init(savedInstanceState)
        addFragment(R.id.content_main, fragment)
    }

    override fun init(savedInstanceState: Bundle?) {
        initializeInjector()
    }

    override fun initializeInjector() {
        component = DaggerMoneyComponent.builder()
            .appComponent(getAppComponent())
            .activityModule(getActivityModule())
            .build()
    }

    override fun getComponent(): MoneyComponent? {
        return component
    }

    override fun showLoading() {
        binding.root.viewLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.root.viewLoading.visibility = View.GONE
    }
}