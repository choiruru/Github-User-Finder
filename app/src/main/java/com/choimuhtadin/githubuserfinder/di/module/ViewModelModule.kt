package com.example.chucknorrisjokes.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.choimuhtadin.githubuserfinder.di.scope.ApplicationScope
import com.example.chucknorrisjokes.di.module.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(SplashViewModel::class)
//    internal abstract fun providesSplashViewModel(viewModel: SplashViewModel) : ViewModel

}