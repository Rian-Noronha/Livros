package com.rn.livros.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@Throws(Throwable::class)
fun <T> LiveData<T>.observeOnce(observer: Observer<T>) {
    observeForever(object : Observer<T> {
        override fun onChanged(value: T) {
            observer.onChanged(value)
            removeObserver(this)
        }

    })
}