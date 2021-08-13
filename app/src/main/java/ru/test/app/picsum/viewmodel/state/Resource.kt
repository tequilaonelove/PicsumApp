package ru.test.app.picsum.viewmodel.state

import java.lang.Exception

data class Resource<out T>(val status: Status, val data: T?, val error: Exception? = null) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(error: Exception?, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data
            )
        }

    }

}