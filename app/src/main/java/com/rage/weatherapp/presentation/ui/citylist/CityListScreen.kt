package com.rage.weatherapp.presentation.ui.citylist

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CityListScreen : SupportAppScreen(){
    override fun getActivityIntent(context: Context): Intent {
        return CityListActivity.getIntent(context)
    }
}