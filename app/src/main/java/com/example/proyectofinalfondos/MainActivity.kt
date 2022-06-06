package com.example.proyectofinalfondos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.proyectofinalfondos.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonIniciar.setOnClickListener {
            if(valoidar(binding.editEmail.text.toString(),binding.editTextContrasenia.text.toString())){
                FirebaseAuth.getInstance().signInWithEmailAndPassword( binding.editEmail.text.toString(),binding.editTextContrasenia.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        mostrarVentana(it.result?.user?.email?:"",ProvideType.BASIC)
                    }else{
                        mensajeError()
                    }
                }
            }// fin del if que valida las cosas

        }// fin del llisenent del boton iniciar secion

        // boton registrar para guardar los daos en firebace
        binding.btnRegistrar.setOnClickListener {
            // validamo que no sena vacions
            if(valoidar(binding.editEmail.text.toString(),binding.editTextContrasenia.text.toString())) {
                // enviamos los datos obenteidos de las cajas
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.editEmail.text.toString(),
                    binding.editTextContrasenia.text.toString()
                ).addOnCompleteListener {
                    // si logro registrar al usuaroo no s manda a la otra ventana
                    if (it.isSuccessful) {
                        mostrarVentana(it.result?.user?.email ?: "", ProvideType.BASIC)
                    } else {
                        // de lo contrario manda un mensaje de eeror
                        mensajeError()
                    }
                }
            }       // fin del if de validar
        }// fin del lisienent de regisrar
    }// fin del onCreate


    fun valoidar(u:String, c: String): Boolean{
        // comprueba qe los datoa quw le llegaron no sean vacios
        if(u==""&& c==""){
            return false
        }
        return true
    }// fin de la funcion de validar vacio

    private fun mensajeError()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("se ha  produciodo un error")
        builder.setPositiveButton("Aceptar",null)
        val dialogo: AlertDialog =  builder.create()
        dialogo.show()
    }

    // metodo para cambiar de ventana
    private fun mostrarVentana(correo:String,provideType: ProvideType){
        val intentPantallaInicio = Intent(this,pantallaPrincipal::class.java).apply {
            putExtra("email",correo)
            putExtra("provider",provideType.name)
        }
        startActivity(intentPantallaInicio)
    }
}// fin de la case