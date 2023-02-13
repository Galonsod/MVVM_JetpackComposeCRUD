package com.example.mvvm30_1.ui.borrar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelBorrar: ViewModel() {

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _error_message = MutableLiveData<String>()
    val error_message: LiveData<String> = _error_message

    private val _confirmation_message = MutableLiveData<String>()
    val confirmation_message: LiveData<String> = _confirmation_message

    private val _isButtonEnable =MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    private fun isValidDNI(id: String): Boolean = id.matches(Regex("^[0-9]{8}[A-Z]$")) && id.length < 10

    fun onCompletedFields(id:String) {
        _id.value = id
        _isButtonEnable.value = enableButton(id)
        _error_message.value = checkTextFieldDNI(id)
        _confirmation_message.value = ""
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

    fun borrarButton(db:FirebaseFirestore, nombre_coleccion:String, id:String){
        db.collection(nombre_coleccion)
            .document(id)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    documentSnapshot.reference.delete()
                        .addOnSuccessListener {
                            _confirmation_message.value = "ÉXITO: Cliente borrado de la base de datos."
                            _id.value = ""
                        }
                        .addOnFailureListener {
                            _confirmation_message.value = "ERROR: Ha habido un error. Por favor inténtelo de nuevo o más tarde."
                        }
                } else {
                    _confirmation_message.value = "ERROR: El cliente buscado no existe en la base de datos."
                }
            }
            .addOnFailureListener {
                _confirmation_message.value = "ERROR: Ha habido un error. Por favor inténtelo de nuevo o más tarde."
            }
    }
}