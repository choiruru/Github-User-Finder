package com.choimuhtadin.githubuserfinder.presentation.base

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.choimuhtadin.githubuserfinder.R

@BindingAdapter("imgUrl")
fun AppCompatImageView.bindImgUrl(url:String){
    Glide.with(context)
        .load(url)
        .circleCrop()
        .placeholder(R.drawable.img_placeholder)
        .into(this)
}