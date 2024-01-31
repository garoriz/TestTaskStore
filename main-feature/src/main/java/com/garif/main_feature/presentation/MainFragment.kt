package com.garif.main_feature.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import com.garif.main_feature.R
import com.garif.main_feature.di.MainFeatureComponentProvider

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainFeatureComponentProvider)
            .getMainFeatureComponent()
            .injectMainFragment(this)
    }
}