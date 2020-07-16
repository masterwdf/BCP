package com.example.bcp.features.splash.di

import com.example.bcp.di.PerActivity
import com.example.bcp.di.component.ActivityComponent
import com.example.bcp.di.component.AppComponent
import com.example.bcp.di.module.ActivityModule
import com.example.bcp.features.splash.SplashFragment
import dagger.Component

@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, SplashModule::class]
)
interface SplashComponent : ActivityComponent {
    fun inject(fragment: SplashFragment)
}