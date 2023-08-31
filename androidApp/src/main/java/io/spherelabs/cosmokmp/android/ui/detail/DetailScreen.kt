package io.spherelabs.cosmokmp.android.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
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
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.android.component.BackButton

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = colorResource(id = R.color.dark_gray),
        topBar = {
            TopDetailBar()
        }
    ) { newPaddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(newPaddingValues)

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
                        text = "Saturn",
                        color = colorResource(id = R.color.white),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        modifier = modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_favourite),
                        contentDescription = null
                    )
                }
                Spacer(modifier = modifier.height(24.dp))
                Text(
                    modifier = modifier.padding(start = 24.dp),
                    text = "Blah Blah",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white).copy(alpha = 0.6F)
                )
            }
        }
    }
}

@Composable
fun TopDetailBar(
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
    DetailScreen()
}
