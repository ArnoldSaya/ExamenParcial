package com.example.quiz_examenparcial

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class AnswerFragment : Fragment() {

    companion object {
        // Método para crear una nueva instancia de AnswerFragment
        fun newInstance(incorrectAnswersCount: Int): AnswerFragment {
            val fragment = AnswerFragment()
            val args = Bundle()
            args.putInt("incorrectAnswersCount", incorrectAnswersCount)  // Pasar el conteo de respuestas incorrectas
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragment
        val view = inflater.inflate(R.layout.fragment_answer, container, false)

        // Obtener el conteo de respuestas incorrectas desde los argumentos
        val incorrectAnswersCount = arguments?.getInt("incorrectAnswersCount") ?: 0
        val feedbackText = view.findViewById<TextView>(R.id.feedback_text)

        // Mostrar el mensaje final con el conteo de respuestas incorrectas
        feedbackText.text = "Completaste el quiz. Te equivocaste en $incorrectAnswersCount preguntas."

        val nextButton: Button = view.findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            // Volver al fragmento de bienvenida
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WelcomeFragment())
                .commit()
        }

        return view
    }
}
