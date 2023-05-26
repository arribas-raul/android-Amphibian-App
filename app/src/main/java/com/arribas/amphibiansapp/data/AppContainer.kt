package com.arribas.amphibiansapp.data

import com.arribas.amphibiansapp.network.AmphibianService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amphibianRepository: AmphibianRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: AmphibianService by lazy {
        retrofit.create(AmphibianService::class.java)
    }

    override val amphibianRepository: AmphibianRepository by lazy {
        DefaulAmphibianRepository(retrofitService)
    }

}