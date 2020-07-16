package com.example.bcp.di.component

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.repository.UserRepository
import com.example.bcp.base.BaseActivity
import com.example.bcp.di.module.AppModule
import com.example.domain.repository.ExchangeRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: BaseActivity)
    fun context(): Context
    fun sharedPreferences(): SharedPreferences
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun userRepository(): UserRepository
    fun exchangeRepository(): ExchangeRepository
}