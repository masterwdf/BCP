package com.example.bcp.features.money.di

import com.example.bcp.di.PerActivity
import com.example.bcp.di.component.ActivityComponent
import com.example.bcp.di.component.AppComponent
import com.example.bcp.di.module.ActivityModule
import com.example.bcp.features.money.MoneyFragment
import dagger.Component

@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, MoneyModule::class]
)
interface MoneyComponent : ActivityComponent {
    fun inject(fragment: MoneyFragment)
}