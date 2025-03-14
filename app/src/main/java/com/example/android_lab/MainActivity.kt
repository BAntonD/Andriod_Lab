package com.example.lab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lab.R

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
