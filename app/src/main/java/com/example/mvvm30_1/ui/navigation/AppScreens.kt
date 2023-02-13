package com.example.mvvm30_1.ui.navigation

// CREAMOS UNA SEALED CLASS PARA CONTENER LOS OBJETOS DE CADA RUTA
sealed class AppScreens(val ruta: String){
    object MenuInicio: AppScreens("MenuInicio")
    object GuardarDatos: AppScreens("GuardarDatos")
    object ModificarDatos: AppScreens("ModificarDatos")
    object BorrarDatos: AppScreens("BorrarDatos")
    object ConsultarDatos: AppScreens("ConsultarDatos")
    object InformeDatos: AppScreens("InformeDatos")
    }
