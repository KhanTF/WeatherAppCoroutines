package com.rage.weatherapp.presentation.common.navigator

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class TransitionSupportAppNavigator : SupportAppNavigator, TransitionNavigator {

    private val activity: FragmentActivity
    private var sharedElements: List<Pair<View, String>> = emptyList()

    constructor(activity: FragmentActivity, containerId: Int) : super(activity, containerId) {
        this.activity = activity
    }

    constructor(activity: FragmentActivity, fragmentManager: FragmentManager?, containerId: Int) : super(activity, fragmentManager, containerId) {
        this.activity = activity
    }

    override fun setSharedElement(sharedElements: List<Pair<View, String>>) {
        this.sharedElements = sharedElements
    }

    override fun createStartActivityOptions(command: Command?, activityIntent: Intent?): Bundle? {
        val pairs = sharedElements.map { android.util.Pair(it.first, it.second) }.toTypedArray()
        return ActivityOptions.makeSceneTransitionAnimation(activity, *pairs).toBundle().apply { sharedElements = emptyList() }
    }

    override fun activityBack() {
        activity.supportFinishAfterTransition()
    }
}