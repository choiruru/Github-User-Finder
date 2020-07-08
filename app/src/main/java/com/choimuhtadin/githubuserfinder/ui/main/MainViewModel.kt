package com.choimuhtadin.githubuserfinder.ui.main

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

    private val _modelSearchUser = MutableLiveData<MutableList<Item>>()
    val modelSearchUser: LiveData<MutableList<Item>> get() = _modelSearchUser

    private val _dataStatus = MutableLiveData<DataStatus>()
    val dataStatus: LiveData<DataStatus> get() = _dataStatus

    private var page:Int=1
    private var totalUsers = 0

    init {
        _modelSearchUser.value = ArrayList()
    }

    fun loadMore(query : String){
        if(totalUsers>_modelSearchUser.value!!.size){
            page++
            searchUsers(query)
        }
    }

    fun search(query : String){
        if(query.length>2)
            networkState(NetworkState.LOADING)
        else
            networkState(NetworkState.LOADED)
        page=1
        _modelSearchUser.value!!.clear()
        searchUsers(query)
    }


    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    private fun searchUsers(query : String){
        disposeLast()
        if(query.length>2){
            lastDisposable = githubRepository.searchUser("$query+in:login", page.toString())
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    totalUsers = it.total_count
                    if(it.total_count>0){
                        if(_modelSearchUser.value!!.size>0 && _modelSearchUser.value!![_modelSearchUser.value!!.size-1].id.isNullOrBlank()){
                            _modelSearchUser.value!!.remove(_modelSearchUser.value!![_modelSearchUser.value!!.size-1])
                        }
                        _modelSearchUser.value!!.addAll(it.items)

                        if(totalUsers>_modelSearchUser.value!!.size){
                            _modelSearchUser.value!!.add(Item())
                        }

                        _modelSearchUser.notifyObserver()

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