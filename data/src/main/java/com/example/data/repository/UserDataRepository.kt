package com.example.data.repository

import com.example.data.mapper.UserEntityDataMapper
import com.example.data.repository.datasource.user.UserDataStoreFactory
import com.example.domain.entity.User
import com.example.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject internal constructor(
    private val userDataStoreFactory: UserDataStoreFactory,
    private val userEntityDataMapper: UserEntityDataMapper
) : UserRepository {

    override fun getUser(): Observable<User?> {
        val dataStore = userDataStoreFactory.createCloud()
        return dataStore.getUser().map { userEntityDataMapper.transform(it) }
    }
}