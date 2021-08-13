package ru.test.app.picsum

import android.app.Application
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import ru.test.app.picsum.core.network.di.DaggerNetworkComponents
import ru.test.app.picsum.di.DaggerAppComponent
import timber.log.Timber


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        initDI()
    }

    private fun initDI() {

        DI.appComponent = DaggerAppComponent.builder()
            .appContext(this)
            .build()

        DI.networkComponent = DaggerNetworkComponents.builder()
            .build()
    }
}