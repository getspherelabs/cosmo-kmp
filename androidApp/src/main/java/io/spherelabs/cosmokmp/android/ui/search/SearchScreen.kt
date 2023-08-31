package io.spherelabs.cosmokmp.android.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.android.component.SearchBar
import io.spherelabs.cosmokmp.search.SearchViewModel
import io.spherelabs.searchdomain.SearchPlanetDomain
import io.spherelabs.searchpresentation.SearchWish

@Composable
fun SearchRoute(
    viewModel: SearchViewModel
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    SearchScreen(
        planets = uiState.value.planets,
        onQueryChanged = { newQuery ->
            viewModel.wish(SearchWish.OnQueryChanged(newQuery))
        },
        isLoading = uiState.value.isLoading
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    planets: List<SearchPlanetDomain>,
    onQueryChanged: (String) -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.dark_gray))
    ) {
        Spacer(modifier = modifier.height(24.dp))
        SearchBar(
            onSearch = { newQuery ->
                onQueryChanged.invoke(newQuery)
            }
        )
        if (isLoading) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (planets.isNotEmpty()) {
            SearchItems(newItems = planets)
        }
    }
}

@Composable
fun SearchItems(
    modifier: Modifier = Modifier,
    newItems: List<SearchPlanetDomain>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        items(
            items = newItems,
            itemContent = { newItem ->
                SearchItem(title = newItem.name, description = newItem.description)
            }
        )
    }
}

@Composable
fun SearchItem(
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

@Preview
@Composable
fun SearchPreviewScreen() {
}
