package com.rage.weatherapp.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseFragment : MvpAppCompatFragment(), BaseView, CoroutineScope by MainScope() {

    abstract val layoutId: Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }

    override fun showErrorMessage(message: String?) {
        if (message != null)
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}