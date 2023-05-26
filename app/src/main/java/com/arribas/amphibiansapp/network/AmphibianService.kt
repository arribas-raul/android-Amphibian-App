package com.arribas.amphibiansapp.network

import com.arribas.amphibiansapp.model.Amphibian
import retrofit2.http.GET

interface AmphibianService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}