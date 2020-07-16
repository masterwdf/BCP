package com.example.bcp.features.exchange.di

import com.example.bcp.di.PerActivity
import com.example.bcp.di.component.ActivityComponent
import com.example.bcp.di.component.AppComponent
import com.example.bcp.di.module.ActivityModule
import com.example.bcp.features.exchange.ExchangeFragment
import dagger.Component

@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, ExchangeModule::class]
)
interface ExchangeComponent : ActivityComponent {
    fun inject(fragment: ExchangeFragment)
}