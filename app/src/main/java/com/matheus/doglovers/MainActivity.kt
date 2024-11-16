package com.matheus.doglovers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.matheus.doglovers.core.presentation.theme.DogLoversTheme
import com.matheus.doglovers.navigation.DogsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogLoversTheme {
                DogsNavHost()
            }
        }
    }
}