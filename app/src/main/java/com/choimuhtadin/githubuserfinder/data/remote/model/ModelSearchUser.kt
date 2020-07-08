package com.choimuhtadin.githubuserfinder.data.remote.model

data class ModelSearchUser(
    val incomplete_results: Boolean,
    val items: MutableList<Item>,
    val total_count: Int
)

data class Item(
    val avatar_url: String,
    val id: String,
    val login: String
) {
    constructor() : this("","","")
}