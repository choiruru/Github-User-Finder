package com.example.chucknorrisjokes.presentation.exception

import com.example.chucknorrisjokes.data.remote.base.Failure


data class NetworkState constructor(
    val status: Status,
    val failure: Failure? = null
) {
    companion object {
        val LOADED =
            NetworkState(Status.SUCCESS)
        val LOADING =
            NetworkState(Status.LOADING)
        val FAILED = NetworkState(Status.FAILED)

        fun error(failure: Failure) = NetworkState(
            Status.FAILED,
            failure
        )
    }
}
