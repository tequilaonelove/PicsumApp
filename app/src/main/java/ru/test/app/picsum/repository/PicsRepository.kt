package ru.test.app.picsum.repository

import ru.test.app.picsum.core.network.model.PicsDto

interface PicsRepository {

suspend fun pics(page: Int): List<PicsDto>

suspend fun likedPics(): List<PicsDto>

suspend fun loadMore(page: Int): List<PicsDto>

suspend fun addToLikedItem(picsDto: PicsDto)

suspend fun deleteItem(picsDto: PicsDto)

}