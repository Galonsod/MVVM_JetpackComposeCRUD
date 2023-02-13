package com.example.mvvm30_1.ui.borrar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
fun BorrarDatos(ViewModelBorrar: ViewModelBorrar) {

    val db = db
    var nombre_coleccion = nombre_coleccion

    val id:String by ViewModelBorrar.id.observeAsState(initial = "")
    val error_message:String by ViewModelBorrar.error_message.observeAsState (initial = "")
    val confirmation_message:String by ViewModelBorrar.confirmation_message.observeAsState("")
    val isButtonEnable:Boolean by ViewModelBorrar.isButtonEnable.observeAsState (initial = false)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.White,
        contentColor = Color.DarkGray,
        border = BorderStroke(1.dp, Color.Blue)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .padding(start = 10.dp)
                .padding(end = 10.dp)

        ) {

            Text(
                text = "Eliminar alumno",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                value = id,
                onValueChange = { ViewModelBorrar.onCompletedFields(id = it) },
                label = { Text("Introduce el NIF a borrar") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            Button(

                onClick = {

                    ViewModelBorrar.borrarButton(db, nombre_coleccion, id)

                },
                enabled = isButtonEnable,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {

                Text(text = "Borrar")

            }

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = error_message, color = Color.Red)
            Text(text = confirmation_message)
        }
    }
}