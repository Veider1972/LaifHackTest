package ru.veider.lifehacktest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import ru.veider.lifehacktest.entities.CompanyData
import ru.veider.lifehacktest.model.MainViewModel
import ru.veider.lifehacktest.model.WEB_PATH

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Company(viewModel: MainViewModel) {
    val company: CompanyData by viewModel.companyDataResponse.observeAsState(CompanyData())
    MaterialTheme() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.global_padding)),
               verticalArrangement = Arrangement.Top
        ) {
            if (viewModel.errorMessage.isEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.aspectRatio(4f)) {
                    if (company.img.isNotEmpty())
                        Image(
                            painter = rememberImagePainter(
                                data = WEB_PATH + company.img
                            ),
                            contentDescription = company.name,
                            modifier = Modifier
                                .weight(0.2f)
                        )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.global_padding))
                            .fillMaxHeight()
                            .weight(0.8f)
                    ) {
                        Text(text = company.name,
                             style = MaterialTheme.typography.subtitle1,
                             fontWeight = FontWeight.Bold
                        )
                        Row {
                            Text(text = "Широта=" + company.lat)
                            Text(text = "Долгота=" + company.lon)
                        }
                        Text(text = "Сайт: " + company.www)
                        Text(text = "Телефон: " + company.phone)
                    }
                }
                Column(Modifier.verticalScroll(state = rememberScrollState())) {
                    Text(text = company.description, modifier = Modifier.padding(top = dimensionResource(id = R.dimen.global_offset)))
                }
            } else
                Text(text = "Сервер выдаёт неверные данные. Получена ошибка:" + viewModel.errorMessage)
        }
    }
}