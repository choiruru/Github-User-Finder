package com.choimuhtadin.githubuserfinder.di.module

import com.choimuhtadin.githubuserfinder.di.module.FragmentBindingModule
import com.choimuhtadin.githubuserfinder.presentation.base.InjectingNavHostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavHostModule {

    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun navHostFragmentInjector(): InjectingNavHostFragment
}