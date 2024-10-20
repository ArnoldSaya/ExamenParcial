package com.example.quiz_examenparcial

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuestionFragment : Fragment() {

    // Lista de preguntas y sus respuestas posibles
    private val questions = listOf(
        "¿Cuál es la capital de Francia?" to listOf("París", "Londres", "Berlín"),
        "¿Cuál es la capital de Alemania?" to listOf("Berlín", "Roma", "Madrid"),
        "¿Cuál es la capital de España?" to listOf("Madrid", "París", "Lisboa"),
        "¿Cuál es la capital de Italia?" to listOf("Roma", "Berlín", "Atenas"),
        "¿Cuál es la capital de Grecia?" to listOf("Atenas", "Lisboa", "Estocolmo")
    )

    // Respuestas correctas para cada pregunta
    private val correctAnswers = listOf("París", "Berlín", "Madrid", "Roma", "Atenas")
    private var currentQuestionIndex = 0  // Índice de la pregunta actual
    private var incorrectAnswersCount = 0  // Contador de respuestas incorrectas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragment
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        displayQuestion(view)  // Mostrar la primera pregunta

        val submitButton: Button = view.findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)
            val selectedOption: RadioButton? = view.findViewById(radioGroup.checkedRadioButtonId)

            if (selectedOption != null) {
                val isCorrect = selectedOption.text == correctAnswers[currentQuestionIndex]

                // Resaltar todas las opciones
                for (i in 0 until radioGroup.childCount) {
                    val radioButton = radioGroup.getChildAt(i) as RadioButton
                    if (radioButton.text == correctAnswers[currentQuestionIndex]) {
                        // Resaltar la opción correcta en verde
                        radioButton.setTextColor(resources.getColor(android.R.color.holo_green_dark))
                    } else {
                        // Resaltar las incorrectas en rojo
                        radioButton.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                    }
                }

                // Contar las respuestas incorrectas
                if (!isCorrect) {
                    incorrectAnswersCount++
                }

                // Verificar si hay más preguntas o si se terminó el quiz
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++  // Pasar a la siguiente pregunta
                    displayQuestion(view)  // Mostrar la siguiente pregunta
                } else {
                    // Mostrar el fragment de respuestas finales
                    val answerFragment = AnswerFragment.newInstance(incorrectAnswersCount)
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, answerFragment)
                        .commit()
                }
            }
        }

        return view
    }

    // Método para mostrar la pregunta actual
    private fun displayQuestion(view: View) {
        val questionText: TextView = view.findViewById(R.id.question_text)
        val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)

        questionText.text = questions[currentQuestionIndex].first  // Mostrar la pregunta
        radioGroup.removeAllViews()  // Limpiar respuestas anteriores

        // Cargar las opciones de respuesta para la pregunta actual
        questions[currentQuestionIndex].second.forEach { option ->
            val radioButton = RadioButton(activity)
            radioButton.text = option
            radioGroup.addView(radioButton)
            radioButton.setTextColor(resources.getColor(android.R.color.black))  // Restablecer color por defecto
        }
    }
}
