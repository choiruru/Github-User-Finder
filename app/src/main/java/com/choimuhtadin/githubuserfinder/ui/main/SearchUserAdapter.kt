package com.choimuhtadin.githubuserfinder.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.choimuhtadin.githubuserfinder.R
import com.choimuhtadin.githubuserfinder.data.remote.model.Item
import com.choimuhtadin.githubuserfinder.databinding.ItemUserBinding

import javax.inject.Inject

class SearchUserAdapter @Inject constructor() :
    ListAdapter<Item,  SearchUserAdapter.UserViewHolder>(object : DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

}) {
    private val TAG = "SearchJokeAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  UserViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Log.d(TAG, "position: "+position);
        holder.bind(getItem(position))
    }

    class UserViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}