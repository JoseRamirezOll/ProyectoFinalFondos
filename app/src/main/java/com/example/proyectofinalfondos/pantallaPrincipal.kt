package com.example.proyectofinalfondos

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyectofinalfondos.databinding.ActivityPantallaPrincipalBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

enum class  ProvideType{
    BASIC

}
class pantallaPrincipal : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaPrincipalBinding
    private var  bd = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // recuperamos los datos de la pantalla de inicio
        var bundle : Bundle? =  intent.extras
        var email: String? = bundle?.getString("email")
        binding =  ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        title = "Inicio"
        setContentView(binding.root)
        bd.collection("imagenes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        binding.btnAgregar.setOnClickListener {

            val intentAgregarImagen = Intent( this,agregarImagen::class.java).apply {
                putExtra("correo",email)
            }
           startActivity(intentAgregarImagen)
        }
    }// fin del onCreate
} // fin de la case pantallaPrincipal