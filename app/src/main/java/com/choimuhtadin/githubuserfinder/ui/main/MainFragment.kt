package com.choimuhtadin.githubuserfinder.ui.main

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.choimuhtadin.githubuserfinder.BR
import com.choimuhtadin.githubuserfinder.R
import com.choimuhtadin.githubuserfinder.databinding.FragmentMainBinding
import com.choimuhtadin.githubuserfinder.presentation.base.BaseFragment
import com.choimuhtadin.githubuserfinder.utils.OnScrollListener
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment @Inject constructor() : BaseFragment<FragmentMainBinding, MainViewModel>() {
    private val TAG = "MainFragment"

    private lateinit var adapter:SearchUserAdapter
    private lateinit var onScrollListener: OnScrollListener

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewReady(savedInstance: Bundle?) {
        initSearch()
        initRecyclerview()
        initViewModel()
        initErrorView()
    }

    private fun initSearch(){
        binding.edtSearch.addTextChangedListener {
                text: Editable? ->
            if(!text.isNullOrEmpty()){
                onScrollListener.reset()
                viewModel.search(text.toString())
            }
        }
    }

    private fun initRecyclerview() {
        onScrollListener = OnScrollListener(recyclerview.layoutManager as LinearLayoutManager) {
            viewModel.loadMore(edtSearch.text.toString())
        }
        adapter = SearchUserAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addOnScrollListener(onScrollListener)

    }

    private fun initViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.modelSearchUser.observe(this, Observer {
            Log.d(TAG, "size: "+it.size);
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initErrorView(){
        binding.lytOffline.btnRetry.setOnClickListener {
            onScrollListener.reset()
            viewModel.search(binding.edtSearch.text.toString())
        }
    }
}