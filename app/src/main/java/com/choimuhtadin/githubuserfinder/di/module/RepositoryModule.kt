package com.choimuhtadin.githubuserfinder.di.module

import com.choimuhtadin.githubuserfinder.data.remote.api.GithubApi
import com.choimuhtadin.githubuserfinder.data.repository.GithubRepositoryImpl
import com.choimuhtadin.githubuserfinder.di.scope.ApplicationScope
import com.choimuhtadin.githubuserfinder.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideJokeRepository(service: GithubApi): GithubRepository {
        return GithubRepositoryImpl(service)
    }
}