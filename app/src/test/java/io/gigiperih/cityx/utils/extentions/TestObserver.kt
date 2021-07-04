package io.gigiperih.cityx.utils.extentions

import androidx.lifecycle.MutableLiveData
import io.gigiperih.cityx.utils.TestObserver

fun <T> MutableLiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}