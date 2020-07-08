package com.choimuhtadin.githubuserfinder.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.choimuhtadin.githubuserfinder.R
import com.choimuhtadin.githubuserfinder.data.remote.model.Item
import com.choimuhtadin.githubuserfinder.databinding.ItemLoadMoreBinding
import com.choimuhtadin.githubuserfinder.databinding.ItemUserBinding

import javax.inject.Inject

class SearchUserAdapter @Inject constructor() :
    ListAdapter<Item,  RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

}) {
    private val TAG = "SearchJokeAdapter"

    private val POST_TYPE_USER = 1
    private val POST_TYPE_LOADING = 2

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).id.isNotEmpty())POST_TYPE_USER else POST_TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        if(viewType == POST_TYPE_USER){
            val binding = DataBindingUtil.inflate<ItemUserBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
            )
            return UserViewHolder(binding)
        }else{
            val binding = DataBindingUtil.inflate<ItemLoadMoreBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_load_more,
                parent,
                false
            )
            return LoadingViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == POST_TYPE_USER){
            val vHolder = holder as UserViewHolder
            vHolder.bind(getItem(position))
        }else{
            val vHolder = holder as LoadingViewHolder
            vHolder.bind(getItem(position))
        }
    }

    class UserViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class LoadingViewHolder(var binding: ItemLoadMoreBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item){
            binding.item = item
            binding.executePendingBindings()
        }
    }
}