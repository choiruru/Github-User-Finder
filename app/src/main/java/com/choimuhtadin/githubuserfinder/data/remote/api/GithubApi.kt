package com.choimuhtadin.githubuserfinder.data.remote.api

import com.choimuhtadin.githubuserfinder.data.remote.model.ModelSearchUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    fun search(
        @Query("q", encoded = true) query:String,
        @Query("page") page:String
    ): Single<ModelSearchUser>
}