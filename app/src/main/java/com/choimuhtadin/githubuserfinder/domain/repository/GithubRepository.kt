package com.choimuhtadin.githubuserfinder.domain.repository

import com.choimuhtadin.githubuserfinder.data.remote.model.ModelSearchUser
import io.reactivex.Single

interface GithubRepository {
    fun searchUser(query:String, page:String):Single<ModelSearchUser>
}