package com.example.data.repository.datasource.user

import android.content.Context
import com.example.data.entity.UserEntity
import io.reactivex.Observable

/**
 * Clase encargada de obtener la data desde el SQLite
 * dicha clase se instancaria una sola vez y se mantendra mientras la aplicacion este viva
 */

internal class UserDBDataStore(private val context: Context) : UserDataStore {

    fun getContext(): Context {
        return context
    }

    override fun getUser(): Observable<UserEntity?> {
        return throw UnsupportedOperationException("")
    }
}
