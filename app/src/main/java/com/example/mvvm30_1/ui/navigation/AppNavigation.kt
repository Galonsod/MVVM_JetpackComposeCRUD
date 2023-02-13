package com.example.mvvm30_1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm30_1.ui.borrar.BorrarDatos
import com.example.mvvm30_1.ui.borrar.ViewModelBorrar
import com.example.mvvm30_1.ui.consultar.ConsultarDatos
import com.example.mvvm30_1.ui.consultar.ViewModelConsultar
import com.example.mvvm30_1.ui.guardar.GuardarDatos
import com.example.mvvm30_1.ui.guardar.ViewModelGuardar
import com.example.mvvm30_1.ui.informe.InformeDatos
import com.example.mvvm30_1.ui.informe.ViewModelInforme
import com.example.mvvm30_1.ui.menu_inicio.MenuInicio
import com.example.mvvm30_1.ui.menu_inicio.ViewModelMenu
import com.example.mvvm30_1.ui.modificar.ModificarDatos
import com.example.mvvm30_1.ui.modificar.ViewModelModificar


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.MenuInicio.ruta)

    {

        composable(AppScreens.MenuInicio.ruta) { MenuInicio(ViewModelMenu(), navigationController) }
        composable(AppScreens.GuardarDatos.ruta) { GuardarDatos(ViewModelGuardar()) }
        composable(AppScreens.ModificarDatos.ruta) { ModificarDatos(ViewModelModificar()) }
        composable(AppScreens.BorrarDatos.ruta) { BorrarDatos(ViewModelBorrar()) }
        composable(AppScreens.ConsultarDatos.ruta) { ConsultarDatos(ViewModelConsultar()) }
        composable(AppScreens.InformeDatos.ruta) { InformeDatos(ViewModelInforme()) }

    }
}