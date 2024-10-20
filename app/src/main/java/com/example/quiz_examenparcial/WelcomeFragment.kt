package com.example.quiz_examenparcial

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragment
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        val startButton: Button = view.findViewById(R.id.start_button)
        startButton.setOnClickListener {
            // Iniciar el quiz al presionar el botón de inicio
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, QuestionFragment())
                .commit()
        }

        return view
    }
}
