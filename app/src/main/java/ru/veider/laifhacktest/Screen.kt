package ru.veider.laifhacktest

sealed class Screen(val route:String) {
    object CompaniesList:Screen("companies_list")
    object CompanyDescription:Screen("company_description")
}