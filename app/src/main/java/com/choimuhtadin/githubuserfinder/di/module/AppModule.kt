package com.choimuhtadin.githubuserfinder.di.module

import com.choimuhtadin.githubuserfinder.di.scope.ApplicationScope
import com.choimuhtadin.githubuserfinder.domain.utils.SchedulerProvider
import com.choimuhtadin.githubuserfinder.presentation.AppSchedulerProvider
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}