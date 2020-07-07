package com.example.chucknorrisjokes.di.module

import com.example.chucknorrisjokes.presentation.base.InjectingNavHostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavHostModule {

    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun navHostFragmentInjector(): InjectingNavHostFragment
}