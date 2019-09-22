package com.rage.weatherapp.presentation.base

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseFragment : MvpAppCompatFragment(), BaseView, CoroutineScope by MainScope() {

    companion object {
        private const val KEY_TRANSITION_NAME = "com.rage.weatherapp.presentation.base.BaseFragment.TRANSITION_NAME"
    }

    abstract val layoutId: Int

    @Parcelize
    data class SharedTransitionName(val id: Int, val name: String) : Parcelable

    fun setTransitionName(names: List<SharedTransitionName>) {
        arguments?.putParcelableArrayList(KEY_TRANSITION_NAME, ArrayList(names))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false).apply {
            val transitions = arguments?.getParcelableArrayList<SharedTransitionName>(KEY_TRANSITION_NAME)
            if (transitions != null) {
                for ((id, name) in transitions) {
                    val view = findViewById<View>(id) ?: continue
                    ViewCompat.setTransitionName(view, name)
                }
            }
        }
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