package com.example.data.net.service.impl

import android.content.Context
import com.example.data.entity.MoneyEntity
import com.example.data.net.service.IExchangeService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.io.InputStream
import java.nio.charset.Charset


class ExchangeService(
    context: Context?,
    url: String?,
    token: String?
) :
    BaseService(context), IExchangeService {
    //private val service: IUserService = RestApi.create(IUserService::class.java, url, token)!!

    override fun getCurrentMoney(): Observable<List<MoneyEntity?>?> {
        return Observable.create { emitter: ObservableEmitter<List<MoneyEntity?>?> ->
            try {

                val json: String

                json = try {
                    val `is`: InputStream = context.assets.open("current_money.json")
                    val size: Int = `is`.available()
                    val buffer = ByteArray(size)
                    `is`.read(buffer)
                    `is`.close()
                    String(buffer, Charset.forName("UTF-8"))
                } catch (exception: Exception) {
                    return@create
                }

                val objectArray = Gson().fromJson<List<MoneyEntity>>(
                    json,
                    object : TypeToken<List<MoneyEntity>>() {}.type
                )

                emitter.onNext(objectArray)
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    /** */

    override fun getMoney(): Observable<List<MoneyEntity?>?> {
        return Observable.create { emitter: ObservableEmitter<List<MoneyEntity?>?> ->
            try {

                val json: String

                json = try {
                    val `is`: InputStream = context.assets.open("money.json")
                    val size: Int = `is`.available()
                    val buffer = ByteArray(size)
                    `is`.read(buffer)
                    `is`.close()
                    String(buffer, Charset.forName("UTF-8"))
                } catch (exception: Exception) {
                    return@create
                }

                //val object = Gson().fromJson(json, MoneyEntity::class.java)
                val objectArray = Gson().fromJson<List<MoneyEntity>>(
                    json,
                    object : TypeToken<List<MoneyEntity>>() {}.type
                )

                emitter.onNext(objectArray)
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }
}