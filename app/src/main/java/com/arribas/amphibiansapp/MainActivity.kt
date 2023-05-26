package com.arribas.amphibiansapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arribas.amphibiansapp.ui.screens.AmphibianViewModel
import com.arribas.amphibiansapp.ui.theme.AmphibiansAppTheme
import com.arribas.amphibiansapp.ui.theme.AppScreen
import com.arribas.amphibiansapp.ui.theme.HomeScreen

class MainActivity : ComponentActivity() {
    private val viewModel: AmphibianViewModel by viewModels { AmphibianViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiansAppTheme {
                // A surface container using the 'background' color from the theme
                AppScreen(amphibianViewModel = viewModel)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AmphibiansAppTheme {
            AppScreen(amphibianViewModel = viewModel)
        }
    }
}

