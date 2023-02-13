package com.example.mvvm30_1.ui.informe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelInforme: ViewModel() {

    private val _datos = MutableLiveData<String>()
    val datos: LiveData<String> = _datos


    fun informeButton(db:FirebaseFirestore, nombre_coleccion:String){ // VACIAMOS VARIABLE AL DAR AL BOTON
        _datos.value = ""

        // HACEMOS LA CONSULTA A LA COLECCION CON GET
        db.collection(nombre_coleccion)
            .get()

            //SI SE CONECTA CORRECTAMENTE
            // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENO EN DATOS
            .addOnSuccessListener { resultado ->
                for (encontrado in resultado) {

                    _datos.value += "DNI: ${encontrado.getString("nif")}\n" //OJO nif o id
                    _datos.value += "Nombre: ${encontrado.getString("nombre")}\n"
                    _datos.value += "Dirección: ${encontrado.getString("direccion")}\n"
                    _datos.value += "Correo electrónico: ${encontrado.getString("mail")}\n"
                    _datos.value += "Teléfono: ${encontrado.getString("tlf")}\n\n"

                }

                if (_datos.toString().isEmpty()) {
                    _datos.value = "ERROR: No existen datos en la base de datos."
                }
            }
            //SI NO CONECTA CORRECTAMENTE
            .addOnFailureListener { resultado ->
                _datos.value = "ERROR: Ha habido un error. Por favor inténtelo de nuevo o más tarde."
            }
    }

}