package com.choimuhtadin.githubuserfinder.ui.main

import android.os.Bundle
import com.choimuhtadin.githubuserfinder.R
import com.choimuhtadin.githubuserfinder.databinding.ActivityMainBinding
import com.choimuhtadin.githubuserfinder.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}