package com.rage.weatherapp.presentation.common.navigation

import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionSet
import com.rage.weatherapp.presentation.base.BaseFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class SharedSupportAppNavigator : SupportAppNavigator {

    data class SharedParams(val sharedElement: List<Triple<View, Int, String>>,
                            val sharedEnterTransition: Transition,
                            val sharedReturnTransition: Transition,
                            val enterTransition: Transition? = TransitionSet(),
                            val exitTransition: Transition? = TransitionSet()) {
        constructor(sharedElement: Triple<View, Int, String>,
                    sharedEnterTransition: Transition,
                    sharedReturnTransition: Transition,
                    enterTransition: Transition? = TransitionSet(),
                    exitTransition: Transition? = TransitionSet()) : this(listOf(sharedElement), sharedEnterTransition, sharedReturnTransition, enterTransition, exitTransition)
    }

    constructor(activity: FragmentActivity, containerId: Int) : super(activity, containerId)
    constructor(activity: FragmentActivity, fragmentManager: FragmentManager, containerId: Int) : super(activity, fragmentManager, containerId)

    override fun setupFragmentTransaction(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        val screen = when (command) {
            is Forward -> command.screen
            is Replace -> command.screen
            else -> null
        }
        if (screen is SharedSupportAppScreen) {
            setupSharedScreen(screen, currentFragment, nextFragment, fragmentTransaction)
        }
    }

    private fun setupSharedScreen(sharedSupportAppScreen: SharedSupportAppScreen, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        fragmentTransaction?.apply {
            val params = sharedSupportAppScreen.getSharedParams()
            val shared = params.sharedElement
            currentFragment?.exitTransition = params.exitTransition
            currentFragment?.sharedElementReturnTransition = params.sharedReturnTransition
            nextFragment?.enterTransition = params.enterTransition
            nextFragment?.sharedElementEnterTransition = params.sharedEnterTransition
            val target = mutableListOf<BaseFragment.SharedTransitionName>()
            for ((view, targetId, name) in shared) {
                ViewCompat.setTransitionName(view, name)
                fragmentTransaction.addSharedElement(view, name)
                target.add(BaseFragment.SharedTransitionName(targetId, name))
            }
            if (nextFragment is BaseFragment) {
                nextFragment.setTransitionName(target)
            }
        }
    }
}