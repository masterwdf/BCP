package com.example.domain.interactor

import com.example.domain.entity.User
import com.example.domain.executor.PostExecutionThread

import com.example.domain.executor.ThreadExecutor
import com.example.domain.repository.UserRepository

import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor?,
    postExecutionThread: PostExecutionThread?
) :
    UseCase(threadExecutor!!, postExecutionThread!!) {

    fun getUser(observer: BaseObserver<User?>) {
        execute(this.userRepository.getUser(), observer)
    }
}
