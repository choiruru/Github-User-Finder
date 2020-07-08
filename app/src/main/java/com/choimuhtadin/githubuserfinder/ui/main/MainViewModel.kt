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

    init {
        _modelSearchUser.value = ArrayList()
    }

    fun loadMore(query : String){
        page++
        search(query)
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    fun search(query : String){
        networkState(NetworkState.LOADING)
        disposeLast()
        if(query.length>2){
            lastDisposable = githubRepository.searchUser("$query+in:login", page.toString())
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    if(it.total_count>0){
                        if(page == 1){
                            _modelSearchUser.value!!.clear()
                            _modelSearchUser.postValue(it.items)
                        }else{
                            _modelSearchUser.value?.addAll(it.items)
                            _modelSearchUser.notifyObserver()
                        }
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