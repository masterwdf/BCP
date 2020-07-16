package com.example.data.repository.datasource.user

import com.example.data.entity.UserEntity
import io.reactivex.Observable

interface UserDataStore {

    fun getUser(): Observable<UserEntity?>
}
