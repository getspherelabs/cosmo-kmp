package io.spherelabs.cosmokmp.android.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.spherelabs.cosmokmp.android.R

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    buttonSize: Int = 48,
    imageSize: Int = 32,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(buttonSize.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(color = colorResource(id = R.color.nero))
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = modifier
                .size(imageSize.dp)
                .padding(start = 8.dp),
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun BackButtonPreview() {
    BackButton() {
    }
}
