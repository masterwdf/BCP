package com.example.bcp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.bcp.di.component.AppComponent
import com.example.bcp.di.component.DaggerAppComponent
import com.example.bcp.di.module.AppModule

class BaseApplication : Application() {

    private var instance: BaseApplication = this
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun getInstance(): BaseApplication {
        return instance
    }
}