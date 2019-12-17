package br.com.fornaro.android.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(viewLifecycleOwner: LifecycleOwner, function: (T) -> Unit) {
    observe(viewLifecycleOwner, Observer {
        function(it)
    })
}

fun Fragment.toast(resId: Int) {
    Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
}