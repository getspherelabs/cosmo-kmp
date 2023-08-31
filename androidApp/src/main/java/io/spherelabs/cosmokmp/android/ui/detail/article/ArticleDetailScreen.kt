package io.spherelabs.cosmokmp.android.ui.detail.article

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.android.component.BackButton
import io.spherelabs.cosmokmp.detail.ArticleDetailViewModel
import io.spherelabs.detaildomain.FavouriteDetailDomain
import io.spherelabs.detailpresentation.article.ArticleDetailState
import io.spherelabs.detailpresentation.article.ArticleDetailWish
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleDetailRoute(
    id: String,
    articleDetailViewModel: ArticleDetailViewModel = koinViewModel()
) {
    val uiState = articleDetailViewModel.state.collectAsStateWithLifecycle()

    ArticleDetailScreen(
        state = uiState.value,
        id = id,
        onFavouriteClick = { newWish ->
            articleDetailViewModel.wish(newWish)
        },
        getArticles = { newWish ->
            articleDetailViewModel.wish(newWish)
        },
        onDeleteFavouriteClick = { newWish ->
            articleDetailViewModel.wish(newWish)
        }
    )
}

@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    state: ArticleDetailState,
    id: String,
    onFavouriteClick: (ArticleDetailWish.InsertFavourite) -> Unit,
    onDeleteFavouriteClick: (ArticleDetailWish.DeleteFavourite) -> Unit,
    getArticles: (ArticleDetailWish.GetArticleByUuid) -> Unit
) {
    LaunchedEffect(key1 = true) {
        getArticles.invoke(ArticleDetailWish.GetArticleByUuid(id))
    }

    val newId = state.article?.id ?: "Unknown"
    val newTitle = state.article?.title ?: "Unknown"
    val newDescription = state.article?.description ?: "Unknown"
    val newAuthor = state.article?.author ?: "Unknown"
    val newCreatedTimestamp = state.article?.createdTimestamp

    val isFavourite = state.isFavourite

    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = colorResource(id = R.color.dark_gray),
        topBar = {
            TopArticleDetailBar()
        }
    ) { newPaddingValues ->
        ArticleDetailContent(
            padding = newPaddingValues,
            title = newTitle,
            description = newDescription,
            isFavourite = isFavourite,
            onFavouriteClick = {
                onFavouriteClick.invoke(
                    ArticleDetailWish.InsertFavourite(
                        FavouriteDetailDomain(
                            id = newId,
                            name = newTitle,
                            description = newDescription,
                            size = "",
                            distanceFromSun = "newDistanceFromSun",
                            isFavourite = true,
                            createdTimestamp = newCreatedTimestamp.toString()
                        )
                    )
                )
            },
            onDeleteFavouriteClick = {
                onDeleteFavouriteClick.invoke(ArticleDetailWish.DeleteFavourite(id))
            }
        )
    }
}

@Composable
fun ArticleDetailContent(
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
fun TopArticleDetailBar(
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
