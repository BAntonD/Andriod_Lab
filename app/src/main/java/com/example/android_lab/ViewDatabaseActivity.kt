package com.example.android_lab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewDatabaseActivity : AppCompatActivity() {

    private lateinit var textViewDatabase: TextView
    private lateinit var buttonBack: Button
    private lateinit var buttonClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_database)

        Log.d("DEBUG", "ViewDatabaseActivity створено")

        textViewDatabase = findViewById(R.id.textViewDatabase)
        buttonBack = findViewById(R.id.buttonBack)
        buttonClear = findViewById(R.id.buttonClear)

        buttonBack.setOnClickListener {
            finish()
        }

        buttonClear.setOnClickListener {
            clearDatabase()
        }

        loadDatabase()
    }

    private fun loadDatabase() {
        try {
            val db = AppDatabase.getDatabase(this)
            val dao = db.questionDao()

            lifecycleScope.launch(Dispatchers.IO) {
                val questions = dao.getAll()

                withContext(Dispatchers.Main) {
                    textViewDatabase.text = if (questions.isEmpty()) {
                        "База даних порожня"
                    } else {
                        questions.joinToString("\n") {
                            "ID: ${it.id}\nПитання: ${it.question}\nСкладність: ${it.complexity}\nТип: ${it.type}\n"
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Помилка у loadDatabase: ${e.message}")
            Toast.makeText(this, "Помилка завантаження БД", Toast.LENGTH_LONG).show()
        }
    }


    private fun clearDatabase() {
        val db = AppDatabase.getDatabase(this)
        val dao = db.questionDao()

        lifecycleScope.launch(Dispatchers.IO) {
            dao.clearAll()

            withContext(Dispatchers.Main) {
                Toast.makeText(this@ViewDatabaseActivity, "База даних очищена", Toast.LENGTH_SHORT).show()
                loadDatabase() // Оновлюємо екран після очищення
            }
        }
    }
}
