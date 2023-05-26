package com.arribas.amphibiansapp.data

import com.arribas.amphibiansapp.model.Amphibian
import com.arribas.amphibiansapp.network.AmphibianService

interface AmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class DefaulAmphibianRepository(
    private val amphibianApiService: AmphibianService
): AmphibianRepository {

    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibianApiService.getAmphibians()
    }

}