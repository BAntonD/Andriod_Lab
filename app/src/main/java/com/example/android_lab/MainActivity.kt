package com.example.lab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lab.R
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.android_lab.AppDatabase
import com.example.android_lab.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var inputFragment: InputFragment
    lateinit var resultFragment: ResultFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ініціалізуємо фрагменти
        inputFragment = supportFragmentManager.findFragmentById(R.id.inputFragment) as InputFragment
        resultFragment = supportFragmentManager.findFragmentById(R.id.resultFragment) as ResultFragment

    }
}
