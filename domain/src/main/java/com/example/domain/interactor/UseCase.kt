package com.example.domain.interactor

import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import dagger.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Clase abstracta encargada de procesar un caso de uso del negocio
 * esta clase guardara todas las ejecuciones realizadas y posteriormente
 * finalizara todas una vez estas han sido terminadas
 */

abstract class UseCase internal constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {
    private val disposables: CompositeDisposable

    /**
     * Ejecuta el actual caso de uso
     *
     * @param observable [Observable]
     * @param observer   [DisposableObserver] which will be listening to the observable build
     * by observable method.
     */

    fun <T> execute(observable: Observable<T>, observer: DisposableObserver<T>?) {
        Preconditions.checkNotNull(observer)
        val disposable: Disposable = observable
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
            .subscribeWith(observer)!!
        addDisposable(disposable)
    }

    /**
     * Finaliza el actual [CompositeDisposable].
     */

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Agrega el [CompositeDisposable].
     */

    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }

    /**
     * Constructor del caso de uso
     *
     * @param threadExecutor      Ejecutor de un metodo
     * @param postExecutionThread Tipo de ejecucion en un hilo diferente
     */

    init {
        disposables = CompositeDisposable()
    }
}