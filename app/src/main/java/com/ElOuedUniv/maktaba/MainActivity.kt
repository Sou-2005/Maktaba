package com.ElOuedUniv.maktaba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ElOuedUniv.maktaba.presentation.MainViewModel
import com.ElOuedUniv.maktaba.presentation.navigation.NavGraph
import com.ElOuedUniv.maktaba.presentation.navigation.Screen
import com.ElOuedUniv.maktaba.presentation.theme.MaktabaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaktabaTheme {
                val isOnboardingCompleted by viewModel.isOnboardingCompleted.collectAsState()

                // Only show NavGraph once we know the onboarding state
                if (isOnboardingCompleted != null) {
                    val startDestination = if (isOnboardingCompleted == true) {
                        Screen.BookList.route
                    } else {
                        Screen.Onboarding.route
                    }
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
