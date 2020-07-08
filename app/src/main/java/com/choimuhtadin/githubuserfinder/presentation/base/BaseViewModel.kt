package com.choimuhtadin.githubuserfinder.presentation.base

import android.util.Log
import androidx.lifecycle.*
import com.choimuhtadin.githubuserfinder.data.remote.base.Failure
import com.choimuhtadin.githubuserfinder.data.remote.base.StatusCode
import com.choimuhtadin.githubuserfinder.presentation.state.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel : ViewModel() {
    private val TAG = "BaseViewModel"

    protected var lastDisposable: Disposable? = null

    private val _networkState = MutableLiveData<NetworkState>()

    val networkState: LiveData<NetworkState> get() = _networkState

    val compositeDisposable = CompositeDisposable()

    /**
     * unsubscribe last subscription rxJava
     */
    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    /**
     * clear all subscription and dispose
     */
    private fun dispose() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

    /**
     * handle error request on throwable as Failure
     * @param throwable wrapp with failure
     */
    fun handleError(throwable: Throwable?) {
        val error = if(throwable is Failure){
            NetworkState.error(throwable)
        } else {
            NetworkState.error(Failure(StatusCode.UNKNOWN_ERROR,"There is unknown error"))
        }
        Log.d(TAG, "Error: "+error.toString());
        _networkState.postValue(error)
    }

    fun networkState(state: NetworkState){
        _networkState.postValue(state)
    }

    /**
     * dispose all subscription when lifecycle onDestory
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onCleared() {
        dispose()
        super.onCleared()
    }
}
