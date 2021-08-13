package ru.test.app.picsum.di;

import android.content.Context;
import androidx.room.Room
import dagger.*
import ru.tequila.apps.radio.deep.ui.utils.AndroidResourceProvider
import ru.tequila.apps.radio.deep.ui.utils.ResourceProvider
import ru.test.app.picsum.persist.AppDatabase

import javax.inject.Singleton;

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun database(): AppDatabase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }

}

@Module
abstract class AppModule {

    companion object {
        @Provides
        @Singleton
        fun database(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .fallbackToDestructiveMigration()
                .build()
    }

}