package com.example.mvvm30_1.ui.consultar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
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
fun ConsultarDatos(ViewModelConsultar: ViewModelConsultar) {

    val db = db
    var nombre_coleccion = nombre_coleccion

    val id:String by ViewModelConsultar.id.observeAsState (initial = "")
    val datos:String by ViewModelConsultar.datos.observeAsState(initial = "")
    val isButtonEnable:Boolean by ViewModelConsultar.isButtonEnable.observeAsState (initial = false)
    val error_message:String by ViewModelConsultar.error_message.observeAsState (initial = "")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .padding(start = 10.dp)
            .padding(end = 10.dp)
    ) {

        Text(
            text = "Búsqueda de clientes por NIF",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        OutlinedTextField(
            value = id,
            onValueChange = { ViewModelConsultar.onCompletedFields(id = it) },
            label = { Text("Introduce el NIF a consultar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        Button(
            onClick = {
                ViewModelConsultar.consultarButton(db, nombre_coleccion, id)
            },

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black),
            enabled = isButtonEnable
        )

        {

            Text(text = "Cargar Datos")
        }

        Spacer(modifier = Modifier.size(10.dp))

        // PINTAMOS EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS

        Text (text = error_message, color = Color.Red)

        Text (text = datos)
    }
}