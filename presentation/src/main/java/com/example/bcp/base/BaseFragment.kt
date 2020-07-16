package com.example.bcp.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.bcp.base.mvp.LoadingView
import com.example.bcp.di.HasComponentJ

abstract class BaseFragment : Fragment(), LoadingView, com.example.bcp.base.mvp.View {

    private var baseActivity: BaseActivity? = null
    protected var loadingView: LoadingView? = null
    private var mIsInjected = false

    init {
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        mIsInjected = try {
            onInjectView()
        } catch (e: IllegalStateException) {
            false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (mIsInjected) onViewInjected(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (!mIsInjected) {
            mIsInjected = onInjectView()

            if (mIsInjected) onViewInjected(savedInstanceState)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is LoadingView) {
            loadingView = context
        }

        if (context is BaseActivity) {
            baseActivity = context
        }
    }

    /** */
    /**
     * Gets a component for dependency injection by its type.
     *
     * @throws IllegalStateException if component has not been initialized yet.
     */

    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponentJ<C>?)!!.component)
            ?: throw IllegalStateException(componentType.simpleName + " has not been initialized yet.")
    }

    /**
     * Called to do an optional injection. This will be called on [.onCreate] and if
     * an exception is thrown or false returned, on [.onActivityCreated] again.
     * Within this method get the injection component and inject the view. Based on returned value
     * [.onViewInjected] will be called. Check [.onViewInjected]
     * documentation for more info.
     *
     * @return True, if injection was successful, false otherwise. Returns false by default.
     * @throws IllegalStateException If there is a failure in getting injection component or
     * injection process itself. This can occur if activity holding
     * component instance has been killed by the system and has not
     * been initialized yet.
     */

    @Throws(IllegalStateException::class)
    protected open fun onInjectView(): Boolean { // Return false by default.
        return false
    }

    /**
     * Called when the fragment has been injected and the field injected can be initialized. This
     * will be called on [.onViewCreated] if [.onInjectView] returned
     * true when executed on [.onCreate], otherwise it will be called on
     * [.onActivityCreated] if [.onInjectView] returned true right before.
     *
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */

    @CallSuper
    protected open fun onViewInjected(savedInstanceState: Bundle?) { // Intentionally left empty.
    }

    /** */

    protected fun showToastMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    /** */

    override fun showLoading() {
        if (loadingView != null) {
            loadingView!!.showLoading()
        }
    }

    override fun hideLoading() {
        if (loadingView != null) {
            loadingView!!.hideLoading()
        }
    }

    protected open fun showKeyboard(editText: EditText?) {
        baseActivity?.showKeyboard(editText)
    }

    protected open fun hideKeyboard() {
        baseActivity?.hideKeyboard()
    }
}