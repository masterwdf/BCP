package com.example.bcp.features.exchange.di

import com.example.bcp.di.PerActivity
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.interactor.ExchangeUseCase
import com.example.domain.repository.ExchangeRepository
import dagger.Module
import dagger.Provides

/**
 * Módulo Dagger que proporciona los casos de usos del Splash.
 */

@Module
class ExchangeModule {
    @Provides
    @PerActivity
    fun providesUserUseCase(
        exchangeRepository: ExchangeRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): ExchangeUseCase {
        return ExchangeUseCase(exchangeRepository, threadExecutor, postExecutionThread)
    }
}
