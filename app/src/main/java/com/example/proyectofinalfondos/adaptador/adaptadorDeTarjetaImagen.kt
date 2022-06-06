package com.example.proyectofinalfondos.adaptador

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class adaptadorDeTarjetaImagen(
    private val context : Context?
): RecyclerView.Adapter<adaptadorDeTarjetaImagen.ImagenCardViewHolder>()
{
    private val bd  = FirebaseFirestore.getInstance()
    // se obtinee los dato de la bd

    class ImagenCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
       // val imageV: ImageView = view!!.findViewById(R)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenCardViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ImagenCardViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}