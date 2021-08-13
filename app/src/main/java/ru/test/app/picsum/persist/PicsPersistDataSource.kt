package ru.test.app.picsum.persist

import ru.test.app.picsum.core.network.model.PicsDto
import ru.test.app.picsum.persist.model.PicsEntity
import javax.inject.Inject

class PicsPersistDataSource @Inject constructor(
    private val picsDao: PicsDao,
) {

    fun pics(): List<PicsDto> = picsDao.getFavoritePics().map { entity ->
        entity.toDto()
    }

    fun saveItem(picsDto: PicsDto) {
        picsDao.saveItem(
            picsDto.toEntity()
        )
    }

    fun deleteItem(picsDto: PicsDto) {
        picsDao.deleteItem(
            picsDto.toEntity()
        )
    }

    private fun PicsEntity.toDto() = PicsDto(
        id.toString(), width, height, author, download_url, url
    )

    private fun PicsDto.toEntity() = PicsEntity(
        id = id.toInt(),
        width = width,
        height = height,
        author = author,
        download_url = imageUrl,
        url = url
    )
}
