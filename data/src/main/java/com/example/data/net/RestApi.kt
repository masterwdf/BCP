package com.example.data.net

/**
 * Clase encargada de realizar las llamadas a un servicio
 * aquí se configura las propiedades de la petición request
 * los interceptores y los convertidores que procesaran la respuesta
 * una vez el servicio haya devuelto un mensaje al cliente
 */

object RestApi {

    private const val TAG = "RestApi"
    private const val CONNECT_TIMEOUT: Long = 30
    private const val READ_TIMEOUT: Long = 30
    private const val WRITE_TIMEOUT: Long = 30

    /*
    private val inkaApi: InkaApi = Builder()
        .baseUrl("")
        .connectTimeout(CONNECT_TIMEOUT)
        .readTimeout(READ_TIMEOUT)
        .writeTimeout(WRITE_TIMEOUT)
        .build()
    */

    /**
     * Metodo que se encarga de crear un servicio con sus respectivos metodos
     *
     * @param clazz Interface que implementa el servicio con sus metodos
     * @param token Token principal donde se encuentra el servicio
     * @param <S>   Generico que toma el tipo de la clase de servicio que se le ha pasado
     * @return object Objeto que contiene los metodos del servicio
    </S> */

    fun <S> create(
        clazz: Class<S>?,
        token: String?,
        drugstore: String?
    ): S? {
        return null
        //return inkaApi.create(clazz, token, drugstore)
    }

    /**
     * Metodo que se encarga de crear un servicio con sus respectivos metodos
     *
     * @param clazz Interface que implementa el servicio con sus metodos
     * @param url   URL principal donde se encuentra el servicio
     * @param token Token principal donde se encuentra el servicio
     * @param <S>   Generico que toma el tipo de la clase de servicio que se le ha pasado
     * @return object Objeto que contiene los metodos del servicio
    </S> */

    fun <S> create(
        clazz: Class<S>?,
        url: String?,
        token: String?,
        drugstore: String?
    ): S? {
        return null
        //turn inkaApi.create(clazz, url, token, drugstore)
    }

    /**
     * Metodo que se encarga de crear un servicio con sus respectivos metodos
     *
     * @param clazz Interface que implementa el servicio con sus metodos
     * @param <S>   Generico que toma el tipo de la clase de servicio que se le ha pasado
     * @return object Objeto que contiene los metodos del servicio
    </S> */

    fun <S> create(clazz: Class<S>?): S? {
        return null
        //return inkaApi.create(clazz)
    }

    /*
    fun parseError(error: Throwable?): ServiceException {
        return try {
            inkaApi.parseError(error)
        } catch (ex: Exception) {
            ServiceException(ex)
        }
    }

    fun readError(params: JsonElement?): ServiceDto {
        return inkaApi.readError(params)
    }

    fun <T> readError(error: JsonElement?, clazz: Class<T>?): T {
        return inkaApi.readError(error, clazz)
    }
    */
}