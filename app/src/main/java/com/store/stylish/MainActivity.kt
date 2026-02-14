package com.store.stylish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.store.stylish.Navigation.AppNavigation
import com.store.stylish.ui.theme.EcommerceApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcommerceApplicationTheme {
                AppNavigation()
            }
        }
    }
}