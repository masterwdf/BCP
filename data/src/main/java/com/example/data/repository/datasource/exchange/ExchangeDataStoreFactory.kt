package com.example.data.repository.datasource.exchange

import android.content.Context
import com.example.data.net.service.IExchangeService
import com.example.data.net.service.impl.ExchangeService
import io.reactivex.annotations.NonNull
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Clase encargada de procesar los datos desde un servicio o base de datos
 * dicha clase se instancaria una sola vez y se mantendra mientras la aplicacion este viva
 */

@Singleton
class ExchangeDataStoreFactory @Inject internal constructor(@NonNull context: Context) {

    private val context: Context

    /**
     * Metodo que crea la clase que obtendra los datos desde el Servicio
     *
     * @return class Clase que se encargara de obtener los datos desde el Servicio
     */

    fun createCloudSoap(): ExchangeDataStore {
        val service: IExchangeService = ExchangeService(context, "", "")
        return ExchangeCloudDataStore(service)
    }

    /**
     * Metodo que crea la clase que obtendra los datos desde el Servicio
     *
     * @return class Clase que se encargara de obtener los datos desde el Servicio
     */

    fun createCloud(): ExchangeDataStore {
        val service: IExchangeService = ExchangeService(
            context,
            "",
            ""
        )

        return ExchangeCloudDataStore(service)
    }

    /**
     * Metodo que crea la clase que obtendra los datos desde el SQLite
     *
     * @return class Clase que se encargara de obtener los datos desde el SQLite
     */

    fun createDB(): ExchangeDataStore {
        return ExchangeDBDataStore(context)
    }

    /**
     * Constructor que se le injecta las dependencias que necesita
     *
     * @param context Contexto que necesita contar el SQLite para ser ejecutado
     */

    init {
        this.context = context.applicationContext
    }
}