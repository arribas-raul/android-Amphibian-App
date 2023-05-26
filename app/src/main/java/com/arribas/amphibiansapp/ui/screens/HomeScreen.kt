package com.arribas.amphibiansapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arribas.amphibiansapp.R
import com.arribas.amphibiansapp.model.Amphibian
import com.arribas.amphibiansapp.ui.screens.AmphibianUiState
import com.arribas.amphibiansapp.ui.screens.AmphibianViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    amphibianViewModel: AmphibianViewModel,
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                amphibianUiState = amphibianViewModel.amphibianUiState
            )
        }
    }
}

@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    modifier: Modifier = Modifier
) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier)
        is AmphibianUiState.Success -> AmphibianList(list = amphibianUiState.list, modifier = modifier)
        is AmphibianUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun AmphibianList(
    list: List<Amphibian>,
    modifier:Modifier = Modifier
){
    LazyColumn(
        contentPadding = PaddingValues(15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
    ) {
        items(list, key = { amphibian -> amphibian.id }) { amphibian ->
            AmphibianCard(
                amphibian = amphibian)
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(width = 0.2.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp))
            .background(color = Color.White),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = amphibian.name,
                modifier = Modifier
                    .padding(5.dp),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = amphibian.description,
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.amphibian_photo),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(1f),
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}


@Preview(showBackground = true)
@Composable
fun AmphibianCardPreview() {
    AmphibiansAppTheme {
        val amphibian = Amphibian(
            id = 1,
            type = "Toad",
            name = "Great Basin Spadefoot",
            description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
            imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
        )
        AmphibianCard(amphibian)
    }
}