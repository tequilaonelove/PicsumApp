package ru.test.app.picsum.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.test.app.picsum.viewmodel.state.Resource
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    val exceptionHandler = CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
        Timber.e(throwable)
    }

    protected fun <T> resourceError(error: Exception?, data: T? = null): Resource<T> {
        return Resource.error(error, data)
    }

    protected fun <T> resourceSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    protected fun <T> resourceLoading(data: T? = null): Resource<T> {
        return Resource.loading(data)
    }

}