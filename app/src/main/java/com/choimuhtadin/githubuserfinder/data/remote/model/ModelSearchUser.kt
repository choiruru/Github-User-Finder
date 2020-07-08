package com.choimuhtadin.githubuserfinder.data.remote.model

data class ModelSearchUser(
    val incomplete_results: Boolean,
    val items: MutableList<Item>,
    val total_count: Int
)

data class Item(
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: String,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)