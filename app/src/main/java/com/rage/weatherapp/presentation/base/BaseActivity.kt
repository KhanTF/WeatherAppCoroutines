package com.rage.weatherapp.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseActivity : MvpAppCompatActivity(), BaseView, CoroutineScope by MainScope() {

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }

    override fun showErrorMessage(message: String?) {
        if (message != null)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}