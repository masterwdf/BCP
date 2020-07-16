package com.example.bcp.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.data.executor.JobExecutor
import com.example.data.repository.UserDataRepository
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.repository.UserRepository
import com.example.bcp.BaseApplication
import com.example.bcp.UIThread
import com.example.data.repository.ExchangeDataRepository
import com.example.domain.repository.ExchangeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Módulo Dagger que proporciona objetos que vivirán durante el ciclo de vida de la aplicación.
 */

@Module
class AppModule(app: BaseApplication) {

    private val app: BaseApplication

    companion object {
        private const val PREF_INKA = "PREF_INKA"
    }

    /**********************************************************/

    init {
        this.app = app
    }

    /**********************************************************/

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_INKA, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDataRepository: UserDataRepository): UserRepository {
        return userDataRepository
    }

    @Provides
    @Singleton
    fun provideExchangeRepository(exchangeDataRepository: ExchangeDataRepository): ExchangeRepository {
        return exchangeDataRepository
    }
}