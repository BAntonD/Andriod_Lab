package com.example.android_lab

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questionInput = findViewById<EditText>(R.id.questionInput)
        val difficultyGroup = findViewById<RadioGroup>(R.id.difficultyGroup)
        val typeGroup = findViewById<RadioGroup>(R.id.typeGroup)
        val okButton = findViewById<Button>(R.id.okButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        okButton.setOnClickListener {
            val question = questionInput.text.toString()
            val difficultyId = difficultyGroup.checkedRadioButtonId
            val typeId = typeGroup.checkedRadioButtonId

            if (question.isEmpty() || difficultyId == -1 || typeId == -1) {
                Toast.makeText(this, "Заповніть всі поля!", Toast.LENGTH_SHORT).show()
            } else {
                val difficulty = findViewById<RadioButton>(difficultyId).text.toString()
                val type = findViewById<RadioButton>(typeId).text.toString()
                resultText.text = "Питання: $question\nСкладність: $difficulty\nТип: $type"
            }
        }
    }
}
