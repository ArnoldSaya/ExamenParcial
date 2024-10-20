package com.example.quiz_examenparcial

import android.app.Activity
import android.app.Fragment
import android.os.Bundle

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Inflar el layout de la actividad principal

        // Cargar el fragmento inicial solo si no hay un estado guardado
        if (savedInstanceState == null) {
            loadFragment(WelcomeFragment())  // Cargar el fragmento de bienvenida
        }
    }

    // Función para reemplazar fragmentos usando el FragmentManager nativo
    private fun loadFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)  // Reemplazar el fragmento actual
            .commit()
    }
}
