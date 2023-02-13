package com.example.mvvm30_1.ui.menu_inicio

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class ViewModelMenu: ViewModel() {

    fun navegaRuta(navController: NavHostController, ruta:String) {
        navController.navigate(ruta)
    }
}