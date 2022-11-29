package ru.veider.lifehacktest

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.veider.lifehacktest.model.MainViewModel
import ru.veider.lifehacktest.ui.companies.CompaniesItem

@Composable
fun Companies(navController: NavController, viewModel: MainViewModel) {
    MaterialTheme() {
        LazyColumn{
            itemsIndexed(items = viewModel.companiesDataListResponse){ index, companies ->
                CompaniesItem(companiesData = companies, navController = navController, viewModel = viewModel)
            }
        }
    }
}