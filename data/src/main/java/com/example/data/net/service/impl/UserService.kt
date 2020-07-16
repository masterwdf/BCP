package com.example.data.net.service.impl

import android.content.Context
import com.example.data.entity.UserEntity
import com.example.data.net.RestApi
import com.example.data.net.service.IUserService
import com.example.domain.entity.User
import io.reactivex.Observable


class UserService(
    context: Context?,
    url: String?,
    token: String?
) :
    BaseService(context), IUserService {
    //private val service: IUserService = RestApi.create(IUserService::class.java, url, token)!!

    /** */

    override fun getUser(): Observable<UserEntity?> {
        return Observable.empty()
    }
}