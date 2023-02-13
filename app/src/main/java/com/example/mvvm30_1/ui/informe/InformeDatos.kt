package com.example.mvvm30_1.ui.informe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mvvm30_1.data.db
import com.example.mvvm30_1.data.nombre_coleccion

@Composable
fun InformeDatos(ViewModelInforme: ViewModelInforme) {

    val db = db
    var nombre_coleccion = nombre_coleccion

    val datos:String by ViewModelInforme.datos.observeAsState(initial = "")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .padding(start = 5.dp)
            .padding(end = 5.dp)
    ) {

        Text(
            text = "Seleccionar todos los datos en Cloud FireStore",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        Button(
            onClick = {

                ViewModelInforme.informeButton(db, nombre_coleccion)

            },

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {

            Text(text = "Cargar Datos")
        }

        Spacer(modifier = Modifier.size(10.dp))

        // PINTAMOS EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS

        Text (text = datos)

    }
}