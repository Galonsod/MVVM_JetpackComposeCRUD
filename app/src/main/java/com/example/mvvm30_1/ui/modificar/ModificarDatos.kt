package com.example.mvvm30_1.ui.modificar

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
fun ModificarDatos(ViewModelModificar: ViewModelModificar) {

    val db = db
    var nombre_coleccion = nombre_coleccion

    val idBuscado:String by ViewModelModificar.id.observeAsState (initial = "")
    val id:String by ViewModelModificar.id.observeAsState (initial = "")
    val nombre:String by ViewModelModificar.nombre.observeAsState (initial = "")
    val direccion:String by ViewModelModificar.direccion.observeAsState (initial = "")
    val mail:String by ViewModelModificar.mail.observeAsState (initial = "")
    val tlf:String by ViewModelModificar.tlf.observeAsState (initial = "")
    val isButtonEnable:Boolean by ViewModelModificar.isButtonEnable.observeAsState (initial = false)
    val error_message:String by ViewModelModificar.error_message.observeAsState (initial = "")
    val confirmation_message:String by ViewModelModificar.confirmation_message.observeAsState(initial = "")
    //val dato:String by ViewModelModificar.dato

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
                .padding(start= 10.dp)
                .padding(end= 10.dp)

        ) {

            Text(
                text = "Modificar Cliente",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = idBuscado,
                onValueChange = { ViewModelModificar.onCompletedFields(idBuscado = it, id = id, nombre = nombre, direccion = direccion, mail = mail, tlf = tlf) },
                label = { Text(text = "Introduce el NIF del cliente cuyos datos se quieren modificar", color = Color.Blue) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = id,
                onValueChange = { ViewModelModificar.onCompletedFields(idBuscado = idBuscado, id = it, nombre = nombre, direccion = direccion, mail = mail, tlf = tlf) },
                label = { Text("Introduce el nuevo NIF. Recuerda que debe ser único") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { ViewModelModificar.onCompletedFields(idBuscado = idBuscado, id = id, nombre = it, direccion = direccion, mail = mail, tlf = tlf) },
                label = { Text("Introduce el nuevo nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            OutlinedTextField(
                value = direccion,
                onValueChange = { ViewModelModificar.onCompletedFields(idBuscado = idBuscado, id = id, nombre = nombre, direccion = it, mail = mail, tlf = tlf) },
                label = { Text("Introduce la nueva dirección") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = mail,
                onValueChange = { ViewModelModificar.onCompletedFields(idBuscado = idBuscado, id = id, nombre = nombre, direccion = direccion, mail = it, tlf = tlf) },
                label = { Text("Introduce el nuevo mail") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = tlf,
                onValueChange = { ViewModelModificar.onCompletedFields(idBuscado = idBuscado, id = id, nombre = nombre, direccion = direccion, mail = mail, tlf = it) },
                label = { Text("Introduce el nuevo teléfono") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(10.dp))

            val dato = hashMapOf(
                "nif" to id.toString(),
                "nombre" to nombre.toString(),
                "direccion" to direccion.toString(),
                "mail" to mail.toString(),
                "tlf" to tlf.toString()
            )

            Button(
                onClick = {

                    ViewModelModificar.modificarButton(db, nombre_coleccion, idBuscado, dato)

                },
                enabled = isButtonEnable,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {

                Text(text = "Modificar")

            }

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = error_message, color = Color.Red)
            Text(text = confirmation_message)
        }
    }
}