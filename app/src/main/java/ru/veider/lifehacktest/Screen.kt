package ru.veider.lifehacktest

sealed class Screen(val route:String) {
    object Companies: Screen("companies_list")
    object Company: Screen("company_description")
}