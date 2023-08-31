package io.spherelabs.cosmokmp.android.ui.favourite

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.favourite.FavouriteViewModel
import io.spherelabs.favouritedomain.FavouriteDomain
import io.spherelabs.favouritepresentation.FavouriteWish

@Composable
fun FavouriteRoute(
    viewModel: FavouriteViewModel
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    FavouriteScreen(
        favourites = uiState.value.favourites,
        onGetStarted = { newWish ->
            viewModel.wish(newWish)
        }
    )
}

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    favourites: List<FavouriteDomain>,
    onGetStarted: (FavouriteWish) -> Unit
) {
    LaunchedEffect(key1 = true) {
        onGetStarted.invoke(FavouriteWish.GetStarted)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = colorResource(id = R.color.dark_gray),
        topBar = {
            TopFavouriteBar()
        }
    ) { newPaddingValues ->
        Column(modifier = modifier.padding(newPaddingValues)) {
            FavouriteItems(newItems = favourites)
        }
    }
}

@Composable
fun FavouriteItems(
    modifier: Modifier = Modifier,
    newItems: List<FavouriteDomain>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        items(
            items = newItems,
            itemContent = { newItem ->
                FavouriteItem(title = newItem.name, description = newItem.description)
            }
        )
    }
}

@Composable
fun FavouriteItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Card(
        modifier = modifier
            .padding(
                top = 24.dp,
                bottom = 24.dp
            )
            .fillMaxWidth()
            .height(150.dp),
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
fun TopFavouriteBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "My favourites",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )
        )
    }
}

@Preview
@Composable
fun FavouritePreviewScreen() {
    FavouriteScreen(favourites = emptyList(), onGetStarted = {})
}
