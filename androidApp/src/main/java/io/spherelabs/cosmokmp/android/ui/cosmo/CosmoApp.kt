package io.spherelabs.cosmokmp.android.ui.cosmo

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import io.spherelabs.cosmokmp.android.MyApplicationTheme
import io.spherelabs.cosmokmp.android.navigation.CosmoNavHost

@Composable
fun CosmoApp() {
    MyApplicationTheme {
        Surface {
            CosmoNavHost()
        }
    }
}
