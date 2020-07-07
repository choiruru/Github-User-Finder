package com.example.chucknorrisjokes.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.chucknorrisjokes.di.module.factory.InjectingFragmentFactory
import com.example.chucknorrisjokes.di.scope.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

//    @Binds
//    @IntoMap
//    @FragmentKey(SplashFragment::class)
//    abstract fun bindSplashFragment(splashFragment: SplashFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}