package ru.test.app.picsum.ui.base

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    val exceptionHandler = CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
        Timber.e(throwable)
    }

    fun Fragment.startToast(msg: String?) {
        Toast.makeText(requireContext(), msg.toString(), Toast.LENGTH_SHORT).show()
    }

}