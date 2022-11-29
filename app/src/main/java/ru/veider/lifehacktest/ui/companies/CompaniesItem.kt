package ru.veider.lifehacktest.ui.companies

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import ru.veider.lifehacktest.Screen
import ru.veider.lifehacktest.entities.CompaniesData
import ru.veider.lifehacktest.model.MainViewModel
import ru.veider.lifehacktest.model.WEB_PATH

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CompaniesItem(companiesData: CompaniesData, navController: NavController, viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp)
            .clickable(
                onClick = {
                    viewModel.run {
                        selectCompany(companiesData.id)
                    }
                    navController.navigate(Screen.Company.route)
                }
            ) ,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Surface() {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = WEB_PATH + companiesData.img
                    ),
                    contentDescription = companiesData.name,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = companiesData.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}