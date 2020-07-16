package com.example.data.mapper

import com.example.data.entity.UserEntity
import com.example.domain.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserEntityDataMapper @Inject
internal constructor() {

    fun transform(user: UserEntity): User {
        return User()
    }
}