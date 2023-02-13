package com.example.mvvm30_1.data

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("StaticFieldLeak")
val db = FirebaseFirestore.getInstance()

var nombre_coleccion = "clientes"

/*val dato = hashMapOf(
    "nif" to GuardarDatos(ViewModelGuardar = , navController = ).
    "nombre" to nombre.toString(),
    "direccion" to direccion.toString(),
    "mail" to mail.toString(),
    "tlf" to tlf.toString()
)*/