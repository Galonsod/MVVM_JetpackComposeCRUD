package com.example.mvvm30_1.ui.modificar

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelModificar: ViewModel() {

    private val _idBuscado = MutableLiveData<String>()
    val idBuscado : LiveData<String> = _idBuscado

    private val _id = MutableLiveData<String>()
    val id : LiveData<String> = _id

    private val _nombre = MutableLiveData<String>()
    val nombre : LiveData<String> = _nombre

    private val _direccion = MutableLiveData<String>()
    val direccion : LiveData<String> = _direccion

    private val _mail = MutableLiveData<String>()
    val mail : LiveData<String> = _mail

    private val _tlf = MutableLiveData<String>()
    val tlf : LiveData<String> = _tlf

    private val _isButtonEnable = MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    private val _error_message = MutableLiveData<String>()
    val error_message: LiveData<String> = _error_message

    private val _confirmation_message = MutableLiveData<String>()
    val confirmation_message: LiveData<String> = _confirmation_message

    fun onCompletedFields(idBuscado:String, id:String, nombre:String, direccion:String,
                          mail:String, tlf:String) {

        _idBuscado.value = idBuscado
        _id.value = id
        _nombre.value = nombre
        _direccion.value = direccion
        _mail.value = mail
        _tlf.value = tlf
        _isButtonEnable.value = enableButton(idBuscado, id, nombre, direccion, mail, tlf)
        _error_message.value = checkTextFields(idBuscado, id, nombre, direccion, mail, tlf)
        _confirmation_message.value = ""
    }

    private fun isValidEmail(mail: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(mail).matches() && mail.length < 50
    private fun isValidDireccion(direccion: String): Boolean = direccion.matches(Regex("^[a-zA-Z0-9\\s]+(?:[\\s,]+[a-zA-Z0-9\\s]+)*\$")) && direccion.length < 50
    private fun isValidPhoneNumber(tlf: String): Boolean = Patterns.PHONE.matcher(tlf).matches() && tlf.length < 26
    private fun isValidDNI(id: String): Boolean = id.matches(Regex("^[0-9]{8}[A-Z]$")) && id.length < 10
    private fun isValidNombre(nombre: String): Boolean = nombre.isNotEmpty() && nombre.length < 30
    //private fun isValidPhoneNumber(tlf: String): Boolean = tlf.matches(Regex("[0-9 ]+")) && tlf.length < 26

    fun enableButton(idBuscado: String, id:String, nombre:String, direccion:String,
                     mail:String, tlf:String) =
        isValidDNI(idBuscado) && isValidDNI(id) && isValidNombre(nombre)
                && isValidDireccion(direccion) && isValidEmail(mail) && isValidPhoneNumber(tlf)

    fun checkTextFields(idBuscado:String, id:String, nombre:String, direccion:String,
                        mail:String, tlf:String): String {
        var error = ""
        if (!isButtonEnable.value!!) {
            if (!isValidDNI(idBuscado)) {
                error += "El NIF buscado debe ser un DNI español, 8 números seguidos de 1 letra mayúscula.\n"
            }
            if (!isValidDNI(id)) {
                error += "El NIF debe ser un DNI español, 8 números seguidos de 1 letra mayúscula.\n"
            }
            if (!isValidNombre(nombre)) {
                error += "Introduzca un nombre y no sobrepase los 30 caracteres.\n"
            }
            if (!isValidDireccion(direccion)) {
                error += "Introduzca una dirección válida y no sobrepase los 50 caracteres.\n"
            }
            if (!isValidEmail(mail)) {
                error += "Introduzca un email válido y no sobrepase los 50 caracteres.\n"
            }
            if (!isValidPhoneNumber(tlf)) {
                error += "Introduzca un número de teléfono válido.\n"
            }
        }
        return error
    }

    fun buttonSuccess(){
        _confirmation_message.value = "ÉXITO: Datos del cliente modificados."
        _idBuscado.value = ""
        _id.value = ""
        _nombre.value = ""
        _direccion.value = ""
        _mail.value = ""
        _tlf.value = ""
    }

    fun buttonFail(){
        //EN CASO DE ERROR DE CONEXION CON LA BBDD NO SE BORRAN LOS DATOS DE LOS CAMPOS
        _confirmation_message.value = "ERROR: Ha habido un error. Por favor inténtelo de nuevo o más tarde."
    }

    fun modificarButton(db: FirebaseFirestore, nombre_coleccion:String, idBuscado: String, dato: HashMap<String, String>){
        db.collection(nombre_coleccion)
            .document(idBuscado)
            .get()
            .addOnSuccessListener { documentSnapshot ->

                //COMPRUEBA QUE EL IDBUSCADO (ID A MODIFICAR) EXISTE EN LA BBDD
                if (documentSnapshot.exists()) {

                    //COMPRUEBA QUE EL NUEVO ID NO EXISTE YA EN LA BBDD PUES DEBE SER ÚNICO, SALVO QUE DICHO ID SEA IGUAL AL IDBUSCADO (NO HAY MODIFICACION DE ID)
                    if (documentSnapshot.data?.get("nif") != id.toString() || idBuscado == id.toString()) {
                        db.collection(nombre_coleccion)
                            .document(idBuscado)
                            .set(dato)
                            .addOnSuccessListener {
                                buttonSuccess()
                            }
                            .addOnFailureListener {
                                buttonFail()
                            }
                    } else {
                        _confirmation_message.value = "ERROR: Nuevo ID introducido ya existe."
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