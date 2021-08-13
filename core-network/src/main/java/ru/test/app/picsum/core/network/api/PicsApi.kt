package ru.test.app.picsum.core.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.test.app.picsum.core.network.model.PicsDto

interface PicsApi {

    @GET("v2/list/")
    suspend fun pics(@Query("page") page: Int): List<PicsDto>

}