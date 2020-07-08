package com.choimuhtadin.githubuserfinder.di.module

import com.choimuhtadin.githubuserfinder.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [NavHostModule::class])
    abstract fun mainActivityInjector(): MainActivity

}