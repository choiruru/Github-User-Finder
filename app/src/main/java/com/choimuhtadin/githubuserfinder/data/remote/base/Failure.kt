package com.choimuhtadin.githubuserfinder.data.remote.base

data class Failure(val code: Int, val msg: String) : Throwable()
