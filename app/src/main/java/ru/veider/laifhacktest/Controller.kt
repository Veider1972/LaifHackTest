package ru.veider.laifhacktest

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@ExperimentalPagerApi
@Composable
fun Controller(navController: NavHostController, startDestination:String):Unit {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Screen.CompaniesList.route) {
            CompaniesList(navController = navController)
        }
        composable(route = Screen.CompanyDescription.route) {
            CompanyDescription(navController = navController)
        }
    }
}