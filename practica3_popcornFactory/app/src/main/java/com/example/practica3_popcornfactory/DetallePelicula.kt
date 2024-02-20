package com.example.practica3_popcornfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica3_popcornfactory.databinding.ActivityDetallePeliculaBinding

class DetallePelicula : AppCompatActivity() {
    private lateinit var binding: ActivityDetallePeliculaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePeliculaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle = intent.extras

        if(bundle != null){
            binding.ivPeliculaImagen.setImageResource(bundle.getInt("header"))
            binding.tvNombrePelicula.setText(bundle.getString("nombre"))
            binding.tvPeliculaDesc.setText(bundle.getString("sinopsis"))
        }
    }
}