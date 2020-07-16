package com.example.bcp.di.component

import android.app.Activity
import com.example.bcp.di.PerActivity

import com.example.bcp.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): Activity
}
