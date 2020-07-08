package com.choimuhtadin.githubuserfinder.di.component

import android.app.Application
import com.choimuhtadin.githubuserfinder.GithubApp
import com.choimuhtadin.githubuserfinder.di.module.*
import com.choimuhtadin.githubuserfinder.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@ApplicationScope
@Component(modules = [
    AppModule::class,
    ActivityModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication?> {

    fun inject(application: GithubApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}