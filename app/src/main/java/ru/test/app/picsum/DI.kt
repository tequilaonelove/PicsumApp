package ru.test.app.picsum

import ru.test.app.picsum.core.network.di.NetworkComponents
import ru.test.app.picsum.di.AppComponent

object DI {
    lateinit var appComponent: AppComponent
        internal set

    lateinit var networkComponent: NetworkComponents
        internal set
}