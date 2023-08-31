package io.spherelabs.cosmokmp.android.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.spherelabs.cosmokmp.android.R

@Composable
fun RoundedImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageSize: Int = 48,
    borderWidth: Int = 6
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.earth),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(imageSize.dp)
            .clip(CircleShape)
            .border(6.dp, Color(0xff63219d), CircleShape)
    )
}
