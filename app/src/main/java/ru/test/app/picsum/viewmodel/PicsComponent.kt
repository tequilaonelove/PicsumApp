package ru.test.app.picsum.viewmodel

import androidx.lifecycle.ViewModel
import dagger.*
import dagger.multibindings.IntoMap
import ru.test.app.picsum.DI
import ru.test.app.picsum.core.network.api.PicsApi
import ru.test.app.picsum.core.network.api.PicsRemoteDataSource
import ru.test.app.picsum.di.ViewModelFactory
import ru.test.app.picsum.di.ViewModelKey
import ru.test.app.picsum.persist.PicsDao
import ru.test.app.picsum.persist.PicsPersistDataSource
import ru.test.app.picsum.repository.PicsRepositoryImpl


@Component(modules = [PicsModule::class])
interface PicsComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun api(api: PicsApi): Builder

        @BindsInstance
        fun picsDao(dao: PicsDao): Builder

        fun build(): PicsComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerPicsComponent.builder()
                .api(DI.networkComponent.api())
                .picsDao(database().picsDao())
                .build()
        }
    }

}

@Module
abstract class PicsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExploreViewModel::class)
    abstract fun exploreViewModel(viewModel: ExploreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun favoriteViewModel(viewModel: FavoriteViewModel): ViewModel

    companion object {
        @Provides
        fun picsRepository(
            remoteDataSource: PicsRemoteDataSource,
            localDataSource: PicsPersistDataSource
        ) = PicsRepositoryImpl(remoteDataSource, localDataSource)
    }
}
