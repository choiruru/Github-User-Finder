package com.example.chucknorrisjokes.presentation.base

import androidx.lifecycle.Observer

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<SingleEvents<T>> {
    override fun onChanged(event: SingleEvents<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}
