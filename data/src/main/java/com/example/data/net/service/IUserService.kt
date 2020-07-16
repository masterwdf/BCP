package com.example.data.net.service

import com.example.data.entity.UserEntity
import io.reactivex.Observable

interface IUserService {

    //@Headers(Constant.TRANSFORM)
    //@POST(value = "api/dispositivo/guardar")
    //@Body
    fun getUser(): Observable<UserEntity?>
}