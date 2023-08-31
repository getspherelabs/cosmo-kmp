package io.spherelabs.cosmokmp.android.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.spherelabs.aboutpresentation.AboutMode
import io.spherelabs.aboutpresentation.AboutWish
import io.spherelabs.cosmokmp.about.AboutViewModel
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.android.component.BackButton
import io.spherelabs.cosmokmp.android.component.LinearLine
import io.spherelabs.cosmokmp.android.component.RoundedImage

@Composable
fun AboutRoute(
    viewModel: AboutViewModel
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    AboutScreen(
        name = uiState.value.name,
        description = uiState.value.description,
        favouritePlanet = uiState.value.favouritePlanet,
        interestedIn = uiState.value.interestedIn,
        mode = uiState.value.mode,
        onEditClick = {
            viewModel.wish(AboutWish.ToggleAboutMode)
        },
        onFavouriteChanged = { newFavourite ->
            viewModel.wish(AboutWish.OnFavouritePlanetChanged(newFavourite))
        },
        onInterestedInChanged = { newInterestedIn ->
            viewModel.wish(AboutWish.OnInterestedInChanged(newInterestedIn))
        }
    )
}

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    favouritePlanet: String,
    interestedIn: String,
    mode: AboutMode,
    onEditClick: () -> Unit,
    onFavouriteChanged: (String) -> Unit,
    onInterestedInChanged: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = colorResource(id = R.color.dark_gray),
        topBar = {
            TopAboutBar { onEditClick.invoke() }
        }
    ) { newPaddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(newPaddingValues)
        ) {
            Spacer(modifier = modifier.height(32.dp))
            RoundedImage(
                modifier = modifier
                    .padding(start = 24.dp),
                imageUrl = "",
                imageSize = 64,
                borderWidth = 8
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                modifier = modifier.padding(start = 24.dp),
                text = name,
                fontSize = 22.sp,
                color = colorResource(id = R.color.white)
            )
            Spacer(modifier = modifier.height(8.dp))
            LinearLine()
            Spacer(modifier = modifier.height(24.dp))
            AboutDescription(description = "Bla bla")
            Spacer(modifier = modifier.height(24.dp))
            FavouritePlanet(
                name = favouritePlanet,
                mode = mode,
                onFavouriteChanged = { newString ->
                    onFavouriteChanged.invoke(newString)
                }
            )
            Spacer(modifier = modifier.height(8.dp))
            LinearLine()
            Spacer(modifier = modifier.height(24.dp))
            InterestedIn(
                category = interestedIn,
                mode = mode,
                onInterestedInChanged = { newString ->
                    onInterestedInChanged.invoke(newString)
                }
            )
        }
    }
}

@Composable
fun AboutDescription(
    modifier: Modifier = Modifier,
    description: String
) {
    Text(
        modifier = modifier.padding(start = 24.dp),
        text = "About",
        fontSize = 22.sp,
        color = colorResource(id = R.color.white)
    )
    Text(
        modifier = modifier.padding(start = 24.dp),
        text = description,
        fontSize = 16.sp,
        color = colorResource(id = R.color.white).copy(alpha = 0.7F)
    )
}

@Composable
fun TopAboutBar(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton {}
        Text(
            modifier = modifier.clickable {
                onEditClick.invoke()
            },
            text = "Edit",
            color = colorResource(id = R.color.white),
            fontSize = 16.sp
        )
    }
}

@Composable
fun FavouritePlanet(
    modifier: Modifier = Modifier,
    name: String,
    mode: AboutMode,
    onFavouriteChanged: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_reshot),
            contentDescription = null
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(text = "Favourite Planet", color = colorResource(id = R.color.white), fontSize = 14.sp)
        Spacer(Modifier.weight(1f))
        if (mode == AboutMode.View) {
            Text(
                text = name,
                textAlign = TextAlign.Left,
                color = colorResource(id = R.color.white).copy(alpha = 0.6F)
            )
        } else {
            BasicTextField(
                value = name,
                textStyle = TextStyle(
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Right
                ),
                maxLines = 1,
                onValueChange = { newString ->
                    onFavouriteChanged.invoke(newString)
                }
            )
        }
    }
}

@Composable
fun InterestedIn(
    modifier: Modifier = Modifier,
    category: String,
    mode: AboutMode,
    onInterestedInChanged: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_alien),
            contentDescription = null
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(text = "Interested in", color = colorResource(id = R.color.white), fontSize = 14.sp)
        Spacer(Modifier.weight(1f))

        if (mode == AboutMode.View) {
            Text(
                text = category,
                textAlign = TextAlign.Left,
                color = colorResource(id = R.color.white).copy(alpha = 0.6F)
            )
        } else {
            BasicTextField(
                value = category,
                textStyle = TextStyle(
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Right
                ),
                maxLines = 1,
                onValueChange = { newString ->
                    onInterestedInChanged.invoke(newString)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouritePlanetWhenAboutEditMode() {
    FavouritePlanet(name = "Mars", mode = AboutMode.Edit, onFavouriteChanged = {})
}

@Preview(showBackground = true)
@Composable
fun FavouritePlanetWhenAboutViewMode() {
    FavouritePlanet(name = "Mars", mode = AboutMode.View, onFavouriteChanged = {})
}
