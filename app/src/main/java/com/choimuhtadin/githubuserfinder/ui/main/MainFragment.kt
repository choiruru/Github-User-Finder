package com.choimuhtadin.githubuserfinder.ui.main

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.choimuhtadin.githubuserfinder.BR
import com.choimuhtadin.githubuserfinder.R
import com.choimuhtadin.githubuserfinder.databinding.FragmentMainBinding
import com.choimuhtadin.githubuserfinder.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment @Inject constructor() : BaseFragment<FragmentMainBinding, MainViewModel>() {
    private val TAG = "MainFragment"

    private lateinit var adapter:SearchUserAdapter

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
    }

    private fun initSearch(){
        binding.edtSearch.addTextChangedListener {
                text: Editable? ->
            if(!text.isNullOrEmpty()){
                viewModel.search(text.toString())
            }
        }
        binding.btnSearch.setOnClickListener {
            viewModel.loadMore(edtSearch.text.toString())
        }
    }

    private fun initRecyclerview() {
        adapter = SearchUserAdapter()

//        binding.recyclerview.addItemDecoration(SpacesItemDecoration(0F, 0F, 6F, 0F))
        binding.recyclerview.adapter = adapter
//        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                hideKeyboard()
//            }
//        })

    }

    private fun initViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.modelSearchUser.observe(this, Observer {
            Log.d(TAG, "size: "+it.size);
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }
}