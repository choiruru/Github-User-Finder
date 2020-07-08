package com.choimuhtadin.githubuserfinder.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.choimuhtadin.githubuserfinder.di.scope.ApplicationScope
import com.choimuhtadin.githubuserfinder.di.module.factory.ViewModelFactory
import com.choimuhtadin.githubuserfinder.ui.main.MainViewModel
import com.choimuhtadin.githubuserfinder.di.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesSplashViewModel(viewModel: MainViewModel) : ViewModel

}