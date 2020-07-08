package com.choimuhtadin.githubuserfinder.data.repository

import com.choimuhtadin.githubuserfinder.data.remote.api.GithubApi
import com.choimuhtadin.githubuserfinder.data.remote.model.ModelSearchUser
import com.choimuhtadin.githubuserfinder.domain.repository.GithubRepository
import com.choimuhtadin.githubuserfinder.data.remote.base.ErrorNetworkHandler
import io.reactivex.Single
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val service: GithubApi
) : GithubRepository , BaseRepository(){

    override fun searchUser(query: String, page:String): Single<ModelSearchUser> {
        return composeSingle { service.search(query, page) }
            .compose(ErrorNetworkHandler())
    }
}
