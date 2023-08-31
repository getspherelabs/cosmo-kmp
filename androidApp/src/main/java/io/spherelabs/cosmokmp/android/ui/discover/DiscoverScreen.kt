package io.spherelabs.cosmokmp.android.ui.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.android.component.RoundedImage
import io.spherelabs.cosmokmp.discover.DiscoverViewModel
import io.spherelabs.discoverdomain.PlanetDomain
import io.spherelabs.discoverpresentation.DiscoverEffect
import io.spherelabs.discoverpresentation.DiscoverState
import io.spherelabs.discoverpresentation.DiscoverWish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiscoverRoute(
    viewModel: DiscoverViewModel = koinViewModel(),
    onNavigateToStars: () -> Unit,
    onNavigateToDetail: (id: String) -> Unit
) {
    DiscoverScreen(
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        state = viewModel.state,
        effect = viewModel.effect,
        onFlyClick = {
            onNavigateToStars.invoke()
        },
        onExplore = { newId ->
            onNavigateToDetail.invoke(newId)
        }
    )
}

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    wish: (DiscoverWish) -> Unit,
    state: StateFlow<DiscoverState>,
    effect: Flow<DiscoverEffect>,
    onFlyClick: () -> Unit,
    onExplore: (id: String) -> Unit
) {
    val scrollState = rememberScrollState()

    val uiState = state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        wish.invoke(DiscoverWish.PopularPlanetsStarted)
        wish.invoke(DiscoverWish.ArticleStarted)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        backgroundColor = colorResource(id = R.color.dark_gray),
        topBar = {
            TopDiscover(url = "", name = "Behzod")
        }
    ) { newPaddingValues ->
        Column(
            modifier = modifier
                .padding(newPaddingValues)
                .verticalScroll(scrollState)
        ) {
            FlyWithCardItem {
                onFlyClick.invoke()
            }
            Spacer(modifier = modifier.height(32.dp))
            PopularTextStars()
            Spacer(modifier = modifier.height(32.dp))
            PopularStars(
                popularStars = uiState.value.popularPlanets,
                isLoading = uiState.value.isLoading
            ) { newId ->
                onExplore.invoke(newId)
            }
            Spacer(modifier = modifier.height(32.dp))
            Text(
                modifier = modifier.padding(start = 24.dp),
                text = stringResource(id = R.string.articles),
                fontSize = 18.sp,
                color = colorResource(id = R.color.white)
            )
            Spacer(modifier = modifier.height(8.dp))
            ArticleItem(
                modifier = modifier,
                title = uiState.value.article?.title ?: "Unknown",
                description = stringResource(id = R.string.dummy_text),
                author = uiState.value.article?.author ?: "Unknown",
                isLoading = uiState.value.isArticleLoading
            )
            Spacer(modifier = modifier.height(24.dp))
        }
    }
}

@Composable
fun TopDiscover(
    modifier: Modifier = Modifier,
    url: String,
    name: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        RoundedImage(imageUrl = url)
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            color = colorResource(id = R.color.white),
            maxLines = 1
        )
    }
}

@Composable
fun FlyWithCardItem(
    modifier: Modifier = Modifier,
    onFlyClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(165.dp)
            .padding(start = 24.dp, end = 24.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = colorResource(id = R.color.nero)
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = modifier.fillMaxHeight()) {
                Text(
                    modifier = modifier.padding(start = 8.dp, top = 8.dp),
                    text = "Fly with \nstars",
                    color = Color.White,
                    fontSize = 24.sp
                )
                Spacer(modifier = modifier.height(24.dp))
                FlyButton {
                    onFlyClick.invoke()
                }
            }

            Column {
                Image(
                    modifier = modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.vr_planet),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun FlyButton(
    modifier: Modifier = Modifier,
    onFlyClick: () -> Unit
) {
    Button(
        modifier = modifier
            .width(width = 93.dp)
            .height(height = 46.dp),
        onClick = { onFlyClick.invoke() },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xff7020c4)
        )
    ) {
        Text(text = "Fly", fontSize = 16.sp, color = colorResource(id = R.color.white))
    }
}

@Composable
fun PopularTextStars(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.popular_stars),
            fontSize = 18.sp,
            color = colorResource(id = R.color.white)
        )
        Text(
            text = stringResource(id = R.string.view_all),
            fontSize = 14.sp,
            color = colorResource(id = R.color.white)
        )
    }
}

@Composable
fun PopularStars(
    modifier: Modifier = Modifier,
    popularStars: List<PlanetDomain>,
    isLoading: Boolean,
    onExplore: (id: String) -> Unit
) {
    LazyRow(
        modifier = modifier
            .padding(start = 14.dp)
            .fillMaxWidth()
    ) {
        items(
            items = popularStars,
            itemContent = { item ->
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    PopularStart(
                        name = item.name,
                        distance = item.distanceFromSun
                    ) {
                        onExplore.invoke(item.id)
                    }
                }
            }
        )
    }
}

@Composable
fun PopularStart(
    modifier: Modifier = Modifier,
    name: String,
    distance: String,
    onExplore: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .width(300.dp)
            .height(200.dp),
        shape = RoundedCornerShape(32.dp),
        backgroundColor = colorResource(id = R.color.nero)
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.earth),
                    contentDescription = null
                )
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name,
                        color = colorResource(id = R.color.white)
                    )
                    Text(text = "0.16", color = colorResource(id = R.color.white))
                    Text(text = distance, color = colorResource(id = R.color.white))
                }
            }
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                onClick = {
                    onExplore.invoke()
                }
            ) {
                Text(text = stringResource(id = R.string.explore))
            }
        }
    }
}

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    author: String,
    isLoading: Boolean
) {
    Card(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(32.dp),
        backgroundColor = colorResource(id = R.color.nero)
    ) {
        Row(modifier = modifier.fillMaxSize()) {
            if (isLoading) {
                Row(
                    modifier = modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Image(
                    modifier = modifier
                        .width(150.dp)
                        .fillMaxHeight(),
                    alignment = Alignment.Center,
                    painter = painterResource(id = R.drawable.earth),
                    contentDescription = null
                )
                Column(
                    modifier = modifier.fillMaxSize()
                ) {
                    Spacer(modifier = modifier.height(32.dp))
                    Text(
                        text = title,
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.white),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    Text(
                        modifier = modifier.padding(end = 8.dp),
                        text = description,
                        maxLines = 4,
                        color = colorResource(id = R.color.white),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DiscoverPreview() {
    DiscoverScreen(
        wish = {},
        state = MutableStateFlow(DiscoverState()),
        effect = MutableStateFlow(DiscoverEffect.Failure("")),
        onFlyClick = {},
        onExplore = {}
    )
}
