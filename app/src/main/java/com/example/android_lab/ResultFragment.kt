package com.example.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android_lab.R

class ResultFragment : Fragment() {

    private lateinit var resultText: TextView
    private lateinit var clearButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.result_fragment, container, false)

        resultText = binding.findViewById(R.id.resultText)
        clearButton = binding.findViewById(R.id.clearButton)

        clearButton.setOnClickListener {
            // Очищаємо результат
            (activity as MainActivity).inputFragment.clearInputs()
            resultText.text = ""
            resultText.visibility = View.GONE
        }

        return binding
    }

    // Оновлюємо результат
    fun updateResult(result: String) {
        resultText.text = result
        resultText.visibility = View.VISIBLE
    }

}
