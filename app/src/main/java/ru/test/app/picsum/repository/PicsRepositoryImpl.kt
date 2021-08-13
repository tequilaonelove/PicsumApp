package ru.test.app.picsum.repository

import kotlinx.coroutines.flow.MutableStateFlow
import ru.test.app.picsum.core.network.api.PicsRemoteDataSource
import ru.test.app.picsum.core.network.model.PicsDto
import ru.test.app.picsum.persist.PicsPersistDataSource
import javax.inject.Inject

class PicsRepositoryImpl @Inject constructor(
    private val remoteDataSource: PicsRemoteDataSource,
    private val localDataSource: PicsPersistDataSource,
) : PicsRepository {

    private val dataState = MutableStateFlow<List<PicsDto>>(listOf())
    private val likedItemsData = MutableStateFlow<List<PicsDto>>(listOf())

    override suspend fun likedPics(): List<PicsDto> {
        return localDataSource.pics()
    }

    override suspend fun pics(page: Int): List<PicsDto> {
        val pics = remoteDataSource.loadingPics(page)
        dataState.emit(
            pics.map { item ->
                item.toMap()
            }.shuffled())
        return dataState.value
    }

    override suspend fun addToLikedItem(picsDto: PicsDto) {
        val data = likedItemsData.value

        if (!localDataSource.pics().contains(picsDto)) {
            localDataSource.saveItem(picsDto)
            likedItemsData.emit(data.plus(picsDto))
        }

    }

    override suspend fun deleteItem(picsDto: PicsDto) {
        val data = likedItemsData.value

        if (localDataSource.pics().contains(picsDto)) {
            localDataSource.deleteItem(picsDto)
            likedItemsData.emit(data.minus(picsDto))
        }
    }

    override suspend fun loadMore(page: Int): List<PicsDto> {
        val currentData = dataState.value
        dataState.emit(currentData)
        try {
            val data = remoteDataSource.loadingPics(page)
            dataState.emit(
                currentData.plus(
                    data.map {
                        it.toMap()
                    }.shuffled()
                )
            )
        } catch (e: Exception) {
            throw e
        }
        return dataState.value
    }

    private fun PicsDto.toMap(): PicsDto {

        // Default value of the key imageUrl: "https://picsum.photos/id/0/5616/3744"
        // Required value imageUrl: https://picsum.photos/id/0/{width}/{height}.webp

        val imageUrl = imageUrl
            .substringBefore("/id/")
            .plus("/id/$id/400/400.webp")

        return PicsDto(
            id = id,
            width = width,
            height = height,
            author = author,
            imageUrl = imageUrl,
            url = url
        )
    }

}