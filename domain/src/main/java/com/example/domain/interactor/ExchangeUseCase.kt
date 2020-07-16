package com.example.domain.interactor

import com.example.domain.entity.Money
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.repository.ExchangeRepository
import javax.inject.Inject

class ExchangeUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    threadExecutor: ThreadExecutor?,
    postExecutionThread: PostExecutionThread?
) :
    UseCase(threadExecutor!!, postExecutionThread!!) {

    fun getCurrentMoney(observer: BaseObserver<List<Money?>?>) {
        execute(this.exchangeRepository.getCurrentMoney(), observer)
    }

    fun getMoney(observer: BaseObserver<List<Money?>?>) {
        execute(this.exchangeRepository.getMoney(), observer)
    }
}
