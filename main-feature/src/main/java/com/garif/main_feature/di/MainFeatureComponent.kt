package com.garif.main_feature.di

import com.garif.main_feature.presentation.MainFragment
import dagger.Subcomponent

@Subcomponent
interface MainFeatureComponent {
    fun injectMainFragment(mainFragment: MainFragment)
}