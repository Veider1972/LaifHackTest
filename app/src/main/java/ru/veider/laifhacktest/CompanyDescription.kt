package ru.veider.laifhacktest

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CompanyDescription(navController: NavController) {
    MaterialTheme() {
        Text(text = "Описание")
    }
}