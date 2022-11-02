package com.lalosapps.madskills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.lalosapps.madskills.ui.MoviesApp
import com.lalosapps.madskills.ui.theme.MADSkillsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalLifecycleComposeApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADSkillsTheme {
                MoviesApp()
            }
        }
    }
}