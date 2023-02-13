package com.example.mvvm30_1.ui.guardar

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
fun GuardarDatos(ViewModelGuardar: ViewModelGuardar) {

    val db = db
    var nombre_coleccion = nombre_coleccion

    val id:String by ViewModelGuardar.id.observeAsState(initial = "")
    val nombre:String by ViewModelGuardar.nombre.observeAsState (initial = "")
    val direccion:String by ViewModelGuardar.direccion.observeAsState (initial = "")
    val mail:String by ViewModelGuardar.mail.observeAsState (initial = "")
    val tlf:String by ViewModelGuardar.tlf.observeAsState (initial = "")
    val isButtonEnable:Boolean by ViewModelGuardar.isButtonEnable.observeAsState (initial = false)
    val error_message:String by ViewModelGuardar.error_message.observeAsState (initial = "")
    val confirmation_message:String by ViewModelGuardar.confirmation_message.observeAsState(initial = "")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 112.dp,
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
                text = "Guardar alumno",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = id,
                onValueChange = { ViewModelGuardar.onCompletedFields(id = it, nombre = nombre, direccion = direccion, mail = mail, tlf = tlf) },
                label = { Text("Introduce el NIF") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { ViewModelGuardar.onCompletedFields(id = id, nombre = it, direccion = direccion, mail = mail, tlf = tlf) },
                label = { Text("Introduce el nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = direccion,
                onValueChange = { ViewModelGuardar.onCompletedFields(id = id, nombre = nombre, direccion = it, mail = mail, tlf = tlf) },
                label = { Text("Introduce la dirección") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = mail,
                onValueChange = { ViewModelGuardar.onCompletedFields(id = id, nombre = nombre, direccion = direccion, mail = it, tlf = tlf) },
                label = { Text("Introduce el email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = tlf,
                onValueChange = { ViewModelGuardar.onCompletedFields(id = id, nombre = nombre, direccion = direccion, mail = mail, tlf = it) },
                label = { Text("Introduce el teléfono") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            val dato = hashMapOf(
                "nif" to id.toString(),
                "nombre" to nombre.toString(),
                "direccion" to direccion.toString(),
                "mail" to mail.toString(),
                "tlf" to tlf.toString()
            )

            Button(

                onClick = {

                    ViewModelGuardar.guardarButton(db, nombre_coleccion, id, dato)

                },
                enabled = isButtonEnable,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    disabledBackgroundColor = Color(0xFF78C8F9),
                    disabledContentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {

                Text(text = "Guardar")

            }

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = error_message, color = Color.Red)
            Text(text = confirmation_message)
        }
    }
}