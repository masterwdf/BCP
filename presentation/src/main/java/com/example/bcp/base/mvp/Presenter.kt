package com.example.bcp.base.mvp

import androidx.annotation.NonNull

interface Presenter<T : View?> {

    /**
     * Método que adjunta la vista en el presentador y interactua con la interfaz. Debe ser llamado en la vista
     */
    fun attachView(@NonNull view: T)

    /**
     * Método que controla el ciclo de vida de la vista. Debe ser llamado en la vista
     * (Activity o Fragment) Método onResume().
     */
    fun resume()

    /**
     * Método que controla el ciclo de vida de la vista. Debe ser llamado en la vista
     * (Activity o Fragment) Método onPause().
     */
    fun pause()

    /**
     * Método que controla el ciclo de vida de la vista. Debe ser llamado en la vista
     * (Activity o Fragment) Método onDestroy().
     */
    fun destroy()
}