package io.spherelabs.cosmokmp.android.ui.detail.star

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.android.component.BackButton
import io.spherelabs.cosmokmp.detail.StarDetailViewModel
import io.spherelabs.detaildomain.FavouriteDetailDomain
import io.spherelabs.detailpresentation.star.StarDetailState
import io.spherelabs.detailpresentation.star.StarDetailWish

@Composable
fun StarDetailRoute(
    viewModel: StarDetailViewModel,
    id: String
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    StarDetailScreen(
        state = uiState.value,
        id = id,
        onFavouriteClick = { newWish ->
            viewModel.wish(newWish)
        },
        getStar = { newWish ->
            viewModel.wish(newWish)
        },
        onDeleteFavouriteClick = { newWish ->
            viewModel.wish(newWish)
        }
    )
}

@Composable
fun StarDetailScreen(
    modifier: Modifier = Modifier,
    state: StarDetailState,
    id: String,
    onFavouriteClick: (StarDetailWish.InsertFavourite) -> Unit,
    onDeleteFavouriteClick: (StarDetailWish.DeleteFavourite) -> Unit,
    getStar: (StarDetailWish.GetStarByUuid) -> Unit
) {
    LaunchedEffect(key1 = true) {
        getStar.invoke(StarDetailWish.GetStarByUuid(id))
    }

    val newId = state.star?.id ?: "Unknown"
    val newTitle = state.star?.name ?: "Unknown"
    val newDescription = state.star?.description ?: "Unknown"
    val newSize = state.star?.size ?: "Unknown"
    val newDistanceFromSun = state.star?.distanceFromSun ?: "Unknown"
    val newCreatedTimestamp = state.star?.createdTimestamp ?: 0L
    val isFavourite = state.isFavourite

    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = colorResource(id = R.color.dark_gray),
        topBar = {
            TopStarDetailBar()
        }
    ) { newPaddingValues ->
        StarDetailContent(
            padding = newPaddingValues,
            title = newTitle,
            description = newDescription,
            isFavourite = isFavourite,
            onFavouriteClick = {
                onFavouriteClick.invoke(
                    StarDetailWish.InsertFavourite(
                        FavouriteDetailDomain(
                            id = newId,
                            name = newTitle,
                            description = newDescription,
                            size = newSize,
                            distanceFromSun = newDistanceFromSun,
                            isFavourite = true,
                            createdTimestamp = newCreatedTimestamp.toString()
                        )
                    )
                )
            },
            onDeleteFavouriteClick = {
                onDeleteFavouriteClick.invoke(StarDetailWish.DeleteFavourite(id))
            }
        )
    }
}

@Composable
fun StarDetailContent(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    title: String,
    description: String,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit,
    onDeleteFavouriteClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)

    ) {
        Spacer(modifier = modifier.height(16.dp))
        Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = modifier
                    .size(150.dp),
                painter = painterResource(id = R.drawable.earth),
                alignment = Alignment.Center,
                contentDescription = null
            )
        }
        Spacer(modifier = modifier.height(32.dp))
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = colorResource(id = R.color.nero),
                    shape = RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp
                    )
                )
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    color = colorResource(id = R.color.white),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    modifier = modifier
                        .size(24.dp)
                        .clickable {
                            if (isFavourite) {
                                onDeleteFavouriteClick.invoke()
                            } else {
                                onFavouriteClick.invoke()
                            }
                        },
                    tint = if (isFavourite) Color.Red else Color.White,
                    imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null
                )
            }
            Spacer(modifier = modifier.height(24.dp))
            Text(
                modifier = modifier.padding(start = 24.dp),
                text = description,
                fontSize = 18.sp,
                color = colorResource(id = R.color.white).copy(alpha = 0.6F)
            )
        }
    }
}

@Composable
fun TopStarDetailBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton {}
    }
}

@Preview
@Composable
fun DetailPreviewScreen() {
    Surface {
//        StarDetailScreen()
    }
}
