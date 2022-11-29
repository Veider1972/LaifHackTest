package ru.veider.lifehacktest.model

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.veider.lifehacktest.entities.CompaniesData
import ru.veider.lifehacktest.entities.CompanyData

class MainViewModel: ViewModel() {

    val apiService = ApiService.getInstance()

    var companiesDataListResponse:List<CompaniesData> by mutableStateOf(listOf())
    private var _companyDataResponse: MutableLiveData<CompanyData> = MutableLiveData(CompanyData())
    val companyDataResponse: LiveData<CompanyData> = _companyDataResponse

    var errorMessage: String by mutableStateOf("")

    init {
        getCompaniesList()
    }

     fun getCompaniesList() {
        viewModelScope.launch {
            try {
                val companiesList = apiService.getCompanies()
                Log.d("TAG", companiesList.toString())
                companiesDataListResponse = companiesList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun selectCompany(id:Int){
        getCompany(id)
    }

    fun getCompany(id:Int){
        viewModelScope.launch {
            errorMessage = ""
            try {
                Log.d("TAG", id.toString())
                val company = apiService.getCompany(id)
                Log.d("TAG", company.toString())
                _companyDataResponse.value = company.first()
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}