package br.com.fornaro.android.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(viewLifecycleOwner: LifecycleOwner, function: (T) -> Unit) {
    observe(viewLifecycleOwner, Observer {
        function(it) })
}