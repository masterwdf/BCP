package com.example.data.repository.datasource.user

import com.example.data.entity.UserEntity
import com.example.data.net.service.IUserService
import io.reactivex.Observable

/**
 * Clase encargada de obtener los datos desde un servicio
 */

internal class UserCloudDataStore(private val service: IUserService) : UserDataStore {

    override fun getUser(): Observable<UserEntity?> {
        return service.getUser()
    }
}
