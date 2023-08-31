package io.spherelabs.cosmokmp.android.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.onboarding.OnboardingViewModel
import io.spherelabs.onboardingpresentation.OnboardingEffect
import io.spherelabs.onboardingpresentation.OnboardingState
import io.spherelabs.onboardingpresentation.OnboardingWish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = koinViewModel(),
    navigateToSignUp: () -> Unit
) {
    OnboardingScreen(
        wish = { newWish ->
            viewModel.wish(newWish)
        },
        state = viewModel.state,
        flow = viewModel.effect,
        navigateToSignUp = {
            navigateToSignUp.invoke()
        }
    )
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    wish: (OnboardingWish) -> Unit,
    state: StateFlow<OnboardingState>,
    flow: Flow<OnboardingEffect>,
    navigateToSignUp: () -> Unit
) {
    val onboardingState = state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        flow.collectLatest {
            when (it) {
                OnboardingEffect.SignUp -> {
                    navigateToSignUp.invoke()
                }

                else -> {}
            }
        }
    }
    Column(
        modifier = modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Text(
            modifier = modifier.padding(top = 42.dp, start = 24.dp),
            text = stringResource(id = R.string.explore_the_universe),
            color = colorResource(id = R.color.white),
            fontSize = 50.sp
        )
        Spacer(modifier = modifier.height(12.dp))
        Text(
            modifier = modifier.padding(start = 24.dp),
            text = stringResource(id = R.string.journey_with_cosmo),
            color = colorResource(id = R.color.pink_swan),
            fontSize = 18.sp
        )
        Spacer(modifier = modifier.height(18.dp))
        Button(
            modifier = modifier
                .height(47.dp)
                .width(174.dp)
                .padding(start = 24.dp),
            onClick = {
                wish.invoke(OnboardingWish.GetStartedClick)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.white),
                contentColor = colorResource(id = R.color.dark_gray)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.get_started),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Box(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Image(
                modifier = modifier
                    .align(Alignment.TopStart)
                    .padding(top = 20.dp),
                painter = painterResource(id = R.drawable.planet_1),
                contentDescription = null
            )
            Image(
                modifier = modifier
                    .align(Alignment.BottomEnd),
                painter = painterResource(id = R.drawable.planet_2),
                contentDescription = null
            )

            Image(
                modifier = modifier
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.planet_3),
                contentDescription = null
            )

            Image(
                modifier = modifier
                    .align(Alignment.BottomStart),
                painter = painterResource(id = R.drawable.planet_4),
                contentDescription = null
            )

            Image(
                modifier = modifier
                    .align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.planet_5),
                contentDescription = null
            )

            Image(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp),
                painter = painterResource(id = R.drawable.frame_23),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    OnboardingScreen(
        wish = {},
        state = MutableStateFlow(OnboardingState()),
        flow = MutableStateFlow(OnboardingEffect.Failure(""))
    ) {
    }
}
