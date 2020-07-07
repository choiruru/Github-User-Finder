package com.example.chucknorrisjokes.data.remote.base

data class Failure(val code: Int, val msg: String) : Throwable()
