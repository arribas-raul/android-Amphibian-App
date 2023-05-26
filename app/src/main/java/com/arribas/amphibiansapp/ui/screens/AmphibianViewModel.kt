package com.arribas.amphibiansapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.arribas.amphibiansapp.AmphibianApplication
import com.arribas.amphibiansapp.data.AmphibianRepository
import com.arribas.amphibiansapp.model.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibianUiState {
    data class Success(val list: List<Amphibian>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}


class AmphibianViewModel(
    val amphibianRepository: AmphibianRepository
): ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibiansData()
    }

    fun getAmphibiansData() {
        viewModelScope.launch {
            amphibianUiState = try {
                AmphibianUiState.Success(amphibianRepository.getAmphibians())

            } catch (e: IOException) {
                AmphibianUiState.Error

            } catch (e: HttpException) {
                AmphibianUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibianApplication)
                val amphibianRepository = application.container.amphibianRepository

                AmphibianViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }
}