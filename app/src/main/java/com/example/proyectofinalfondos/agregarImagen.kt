package com.example.proyectofinalfondos

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyectofinalfondos.databinding.ActivityAgregarImagenBinding
import com.example.proyectofinalfondos.modelo.Imagen
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore


class agregarImagen : AppCompatActivity()
{
    private lateinit var binding: ActivityAgregarImagenBinding
    private var  bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityAgregarImagenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Subir imagen"
        val bundle : Bundle? =  intent.extras
        val email: String? = bundle?.getString("correo")
        binding.textViweCorreo.setText(email.toString())
        var mySnackbar = Snackbar.make(binding.root, R.string.agregadoMensaje, 15)

        // escucha del boton para agregar el nuevo fondo
        binding.btnSubirImagen.setOnClickListener {
            val imagen = Imagen(
                binding.editTituloText.text.toString(),
                binding.editTextLink.text.toString(),
                email.toString()
            )
            if(binding.editTituloText.text.toString()==""&& binding.editTextLink.text.toString() == ""){
                mySnackbar = Snackbar.make(binding.root, "llene los datos por favor", 25)
                mySnackbar.show()
            }else{
                bd.collection(email.toString()).document().set(imagen).addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e)
                        mySnackbar.show()
                    }
            }// fin del if
       }
    }// fin del oncreate

}// fin de la case agregarImagen