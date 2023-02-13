package com.example.mvvm30_1.ui.consultar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelConsultar: ViewModel() {

    private val _id = MutableLiveData<String>()
    val id : LiveData<String> = _id

    private val _datos = MutableLiveData<String>()
    val datos : LiveData<String> = _datos

    private val _isButtonEnable = MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    private val _error_message = MutableLiveData<String>()
    val error_message: LiveData<String> = _error_message

    private fun isValidDNI(id: String): Boolean = id.matches(Regex("^[0-9]{8}[A-Z]$")) && id.length < 10

    fun onCompletedFields(id:String) {
        _id.value = id
        _isButtonEnable.value = enableButton(id)
        _error_message.value = checkTextFieldDNI(id)
    }

    fun enableButton(id:String) = isValidDNI(id)

    fun checkTextFieldDNI(id: String): String {
        var error = ""
        if (!isButtonEnable.value!!) {
            if (!isValidDNI(id)) {
                error += "El NIF a borrar debe ser un DNI español, 8 números seguidos de 1 letra mayúscula."
            }
        }
        return error
    }

    fun consultarButton(db:FirebaseFirestore, nombre_coleccion:String, id:String){
        _datos.value = ""
        db.collection(nombre_coleccion)
            .document(id)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    _datos.value += "DNI: ${documentSnapshot.getString("nif")}\n"
                    _datos.value += "Nombre: ${documentSnapshot.getString("nombre")}\n"
                    _datos.value += "Dirección: ${documentSnapshot.getString("direccion")}\n"
                    _datos.value += "Correo electrónico: ${documentSnapshot.getString("mail")}\n"
                    _datos.value += "Teléfono: ${documentSnapshot.getString("tlf")}\n\n"
                } else {
                    _datos.value = "ERROR: El cliente buscado no existe en la base de datos."
                }
            }
            .addOnFailureListener {
                _datos.value = "ERROR: Ha habido un error. Por favor inténtelo de nuevo o más tarde."
            }
    }

}