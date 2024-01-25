package com.rentwiz.app.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rentwiz.app.compose.navigation.navgraph.SetUpNavGraph
import com.rentwiz.app.compose.viewmodels.ComposeViewModel
import com.rentwiz.app.ui.theme.RentWizTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {

    private val composeViewModel: ComposeViewModel by viewModels()

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentWizTheme {
                BottomNavExample()
            }
        }
    }
}
