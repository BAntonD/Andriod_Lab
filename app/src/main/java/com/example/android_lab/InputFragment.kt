package com.example.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.android_lab.AppDatabase
import com.example.android_lab.R
import androidx.lifecycle.lifecycleScope
import com.example.android_lab.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InputFragment : Fragment() {

    private lateinit var questionInput: EditText
    private lateinit var difficultyGroup: RadioGroup
    private lateinit var typeGroup: RadioGroup
    private lateinit var okButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.input_fragment, container, false)

        questionInput = binding.findViewById(R.id.questionInput)
        difficultyGroup = binding.findViewById(R.id.difficultyGroup)
        typeGroup = binding.findViewById(R.id.typeGroup)
        okButton = binding.findViewById(R.id.okButton)

        okButton.setOnClickListener {
            val question = questionInput.text.toString().trim()
            val difficulty = getSelectedRadioButtonText(difficultyGroup)
            val questionType = getSelectedRadioButtonText(typeGroup)

            if (question.isBlank() || difficulty == "null" || questionType == "null") {
                // Якщо одне з полів порожнє
                Toast.makeText(context, "Заповніть всі поля!", Toast.LENGTH_SHORT).show()
            } else {
                // Формуємо результат
                val resultText = "Питання: $question\nСкладність: $difficulty\nТип питання: $questionType"
                (activity as MainActivity).resultFragment.updateResult(resultText)

                // Додаємо в БД
                val db = AppDatabase.getDatabase(requireContext())
                val dao = db.questionDao()

                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    dao.insert(Question(question = question, complexity = difficulty, type = questionType))

                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Питання збережено!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return binding
    }

    // Отримуємо вибрану опцію з RadioGroup
    fun getSelectedRadioButtonText(radioGroup: RadioGroup): String {
        val selectedId = radioGroup.checkedRadioButtonId
        val selectedRadioButton = view?.findViewById<RadioButton>(selectedId)
        return selectedRadioButton?.text.toString().trim()
    }

    // Очищаємо всі поля
    fun clearInputs() {
        questionInput.text.clear()
        difficultyGroup.clearCheck()
        typeGroup.clearCheck()
    }
}
