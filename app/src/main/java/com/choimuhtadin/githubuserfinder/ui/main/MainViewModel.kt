package com.choimuhtadin.githubuserfinder.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.choimuhtadin.githubuserfinder.data.remote.model.Item
import com.choimuhtadin.githubuserfinder.domain.repository.GithubRepository
import com.choimuhtadin.githubuserfinder.domain.utils.SchedulerProvider
import com.choimuhtadin.githubuserfinder.presentation.base.BaseViewModel
import com.choimuhtadin.githubuserfinder.presentation.state.DataStatus
import com.choimuhtadin.githubuserfinder.presentation.state.NetworkState
import javax.inject.Inject

class MainViewModel  @Inject constructor(
    private val githubRepository: GithubRepository,
    private val schedulers: SchedulerProvider
): BaseViewModel() {
    private val TAG = "MainViewModel"

    private val _modelSearchUser = MutableLiveData<List<Item>>()
    val modelSearchUser: LiveData<List<Item>> get() = _modelSearchUser

    private val _dataStatus = MutableLiveData<DataStatus>()
    val dataStatus: LiveData<DataStatus> get() = _dataStatus

    private var page:Int=1
    private var totalUsers = 0

    fun loadMore(query : String){
        _modelSearchUser.value?.let {
            if(totalUsers>it.size){
                page++
                searchUsers(query)
            }
        }
    }

    fun search(query : String){
        if(query.length>2)
            networkState(NetworkState.LOADING)
        else
            networkState(NetworkState.LOADED)
        page=1
        _modelSearchUser.postValue(mutableListOf())

        searchUsers(query)
    }

    private fun searchUsers(query : String){
        disposeLast()
        if(query.length>2){
            lastDisposable = githubRepository.searchUser("$query+in:login", page.toString())
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({ models ->
                    totalUsers = models.total_count
                    if(models.total_count>0){

                        val temp = _modelSearchUser.value?.toMutableList() ?: mutableListOf()

                        if(temp.isNotEmpty() && temp[temp.size-1].id.isBlank()){
                            temp.removeAt(temp.size-1)
                        }

                        temp.addAll(models.items)

                        if(totalUsers>temp.size){
                            temp.add(Item())
                        }

                        _modelSearchUser.postValue(temp)

                        _dataStatus.postValue(DataStatus.NOT_EMPTY)
                    }else{
                        _dataStatus.postValue(DataStatus.EMPTY)
                    }
                    networkState(NetworkState.LOADED)
                },{
                    handleError(it)
                })

            lastDisposable?.let { compositeDisposable.add(it) }
        }
    }
}