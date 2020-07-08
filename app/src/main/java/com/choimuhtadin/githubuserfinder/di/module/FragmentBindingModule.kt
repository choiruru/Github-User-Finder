package com.choimuhtadin.githubuserfinder.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.choimuhtadin.githubuserfinder.di.module.factory.InjectingFragmentFactory
import com.choimuhtadin.githubuserfinder.ui.main.MainFragment
import com.choimuhtadin.githubuserfinder.di.scope.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    abstract fun bindSplashFragment(splashFragment: MainFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}