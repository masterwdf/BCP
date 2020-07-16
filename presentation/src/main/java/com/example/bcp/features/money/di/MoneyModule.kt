package com.example.bcp.features.money.di

import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.interactor.UserUseCase
import com.example.domain.repository.UserRepository
import com.example.bcp.di.PerActivity
import dagger.Module
import dagger.Provides


/**
 * Módulo Dagger que proporciona los casos de usos del Splash.
 */

@Module
class MoneyModule {
    @Provides
    @PerActivity
    fun providesUserUseCase(
        userRepository: UserRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): UserUseCase {
        return UserUseCase(userRepository, threadExecutor, postExecutionThread)
    }
}
