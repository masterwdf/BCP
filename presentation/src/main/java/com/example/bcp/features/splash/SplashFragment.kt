package com.example.bcp.features.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bcp.base.BaseFragment
import com.example.bcp.databinding.FragmentSplashBinding
import com.example.bcp.features.exchange.ExchangeActivity
import com.example.bcp.features.splash.di.SplashComponent
import javax.inject.Inject

class SplashFragment : BaseFragment(), SplashView {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onInjectView(): Boolean {
        getComponent(SplashComponent::class.java).inject(this)
        return true
    }

    override fun onViewInjected(savedInstanceState: Bundle?) {
        super.onViewInjected(savedInstanceState)
        this.presenter.attachView(this)
        this.presenter.verifyVersion()
    }

    override fun showVerifyVersion() {
        val intent = Intent(activity, ExchangeActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun context(): Context {
        return activity as Context
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
