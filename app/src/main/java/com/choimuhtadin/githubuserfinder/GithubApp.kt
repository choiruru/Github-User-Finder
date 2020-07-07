package com.choimuhtadin.githubuserfinder

import com.choimuhtadin.githubuserfinder.di.component.DaggerAppComponent
import dagger.android.support.DaggerApplication

class GithubApp : DaggerApplication() {

    private val applicationInjector = DaggerAppComponent.builder().application(this).build()

    override fun applicationInjector()= applicationInjector
}