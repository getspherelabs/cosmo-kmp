package io.spherelabs.cosmokmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.spherelabs.cosmokmp.android.ui.cosmo.CosmoApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CosmoApp()
        }
    }
}
