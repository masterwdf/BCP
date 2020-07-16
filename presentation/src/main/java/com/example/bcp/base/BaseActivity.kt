package com.example.bcp.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.bcp.BaseApplication
import com.example.bcp.di.component.AppComponent
import com.example.bcp.di.module.ActivityModule


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        this.getAppComponent()?.inject(this)
    }

    /**********************************************************/

    protected abstract fun init(savedInstanceState: Bundle?)

    protected abstract fun initializeInjector()

    //

    protected open fun addFragment(containerViewId: Int, fragment: Fragment?) {
        addFragment(containerViewId, fragment, true)
    }

    protected open fun addFragment(
        containerViewId: Int,
        fragment: Fragment?,
        isFirst: Boolean
    ) {
        addFragment(containerViewId, fragment, isFirst, "")
    }

    protected open fun addFragment(
        containerViewId: Int,
        fragment: Fragment?,
        isFirst: Boolean,
        name: String?
    ) {
        val fragmentTransaction =
            this.supportFragmentManager.beginTransaction()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        if (name.isNullOrEmpty()) fragmentTransaction.add(
            containerViewId,
            fragment!!
        ) else fragmentTransaction.add(containerViewId, fragment!!, name)
        if (!isFirst) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    protected open fun replaceFragment(
        containerViewId: Int,
        fragment: Fragment?,
        isFirst: Boolean
    ) {
        replaceFragment(containerViewId, fragment, isFirst, "")
    }

    protected open fun replaceFragment(
        containerViewId: Int,
        fragment: Fragment?,
        isFirst: Boolean,
        name: String?
    ) {
        val fragmentTransaction: FragmentTransaction =
            this.supportFragmentManager.beginTransaction()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        if (name.isNullOrEmpty()) fragmentTransaction.replace(
            containerViewId,
            fragment!!
        ) else fragmentTransaction.replace(containerViewId, fragment!!, name)
        if (!isFirst) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**********************************************************/


    protected open fun getAppComponent(): AppComponent? {
        return (application as BaseApplication).getAppComponent()
    }

    protected open fun getActivityModule(): ActivityModule? {
        return ActivityModule(this)
    }

    open fun showKeyboard(editText: EditText?) {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    open fun hideKeyboard() {
        val view: View? = currentFocus

        if (view != null) {
            val inputManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}