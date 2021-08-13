package ru.test.app.picsum.core.network.api

import ru.test.app.picsum.core.network.model.PicsDto
import javax.inject.Inject

class PicsRemoteDataSource @Inject constructor(
  private val api: PicsApi
) {

  suspend fun loadingPics(page: Int): List<PicsDto> {
    return api.pics(page = page)
  }

}