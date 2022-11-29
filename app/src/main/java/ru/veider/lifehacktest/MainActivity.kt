package ru.veider.lifehacktest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import ru.veider.lifehacktest.model.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class, ExperimentalUnitApi::class, ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Companies.route) {
                composable(route = Screen.Companies.route) {
                    Companies(navController = navController, viewModel = viewModel)
                }
                composable(route = Screen.Company.route) {
                    Company(viewModel = viewModel)
                }
            }
        }
    }
}