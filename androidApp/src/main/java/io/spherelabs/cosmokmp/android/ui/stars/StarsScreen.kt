package io.spherelabs.cosmokmp.android.ui.stars

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.android.component.BackButton
import io.spherelabs.cosmokmp.stars.StarsViewModel
import io.spherelabs.starsdomain.StarDomain
import io.spherelabs.starspresentation.StarsState
import io.spherelabs.starspresentation.StarsWish

@Composable
fun StarsRoute(
    viewModel: StarsViewModel,
    onNavigateBack: () -> Unit,
    onNavigateDetail: (newId: String) -> Unit
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    StarsScreen(
        state = uiState.value,
        onGetStarted = { getStartedWish ->
            viewModel.wish(getStartedWish)
        },
        onNavigateBack = {
            onNavigateBack.invoke()
        },
        onDetailClick = { newId ->
            onNavigateDetail.invoke(newId)
        }
    )
}

@Composable
fun StarsScreen(
    modifier: Modifier = Modifier,
    state: StarsState,
    onGetStarted: (StarsWish) -> Unit,
    onNavigateBack: () -> Unit,
    onDetailClick: (id: String) -> Unit
) {
    LaunchedEffect(key1 = true) {
        onGetStarted.invoke(StarsWish.StarsStarted)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = colorResource(id = R.color.dark_gray),
        topBar = {
            TopStarsBar {
                onNavigateBack.invoke()
            }
        }
    ) { newPaddingValues ->
        Column(modifier = modifier.padding(newPaddingValues)) {
            if (state.isLoading) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                StarItems(
                    newItems = state.stars,
                    onDetailClick = { newId ->
                        onDetailClick.invoke(newId)
                    }
                )
            }
        }
    }
}

@Composable
fun StarItems(
    modifier: Modifier = Modifier,
    newItems: List<StarDomain>,
    onDetailClick: (id: String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        items(
            items = newItems,
            itemContent = { newItem ->
                StarItem(
                    title = newItem.name,
                    description = newItem.description
                ) {
                    onDetailClick.invoke(newItem.id)
                }
            }
        )
    }
}

@Composable
fun StarItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onDetailClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(
                top = 24.dp,
                bottom = 24.dp
            )
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                onDetailClick.invoke()
            },
        shape = RoundedCornerShape(32.dp),
        backgroundColor = colorResource(id = R.color.nero)
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
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

@Composable
fun TopStarsBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton {
            onBackClick.invoke()
        }
    }
}
