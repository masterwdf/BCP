package com.example.data.net.service.impl

import android.content.Context

/**
 * Clase base de un servicio
 * que contiene los metodos comunes que involucra cualquier servicio
 */

open class BaseService internal constructor(context: Context?) {

    protected val context: Context

    /**
     * Metodo que valida si tiene conexion a internet
     *
     * @return boolean Valor que determina la conectividad del dispositivo
     */

    /*
    protected val isThereInternetConnection: Boolean
        protected get() = NetworkUtil.isThereInternetConnection(context)

    fun getError(error: Throwable?): Throwable {
        return try {
            val throwable: Throwable = RestApi.parseError(error)
            if (throwable is UnauthorizedException) {
                ExpiredSessionException()
            } else if (throwable is ForbiddenException) {
                ExpiredSessionException()
            } else {
                throwable
            }
        } catch (ex: Exception) {
            ServiceException(ex)
        }
    }
    */

    /**
     * Constructor
     *
     * @param context Contexto que llamo al servicio
     */

    init {
        requireNotNull(context) { "El constructor no puede recibir parametros nulos!!!" }
        this.context = context
    }
}