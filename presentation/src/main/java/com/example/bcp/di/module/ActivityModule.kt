package com.example.bcp.di.module

import android.app.Activity
import com.example.bcp.di.PerActivity
import dagger.Module

import dagger.Provides

/**
 * Módulo Dagger que proporciona objetos que vivirán durante el ciclo de vida de una actividad.
 */

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun provideActivity(): Activity {
        return activity
    }

}