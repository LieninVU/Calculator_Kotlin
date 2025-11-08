package com.example.calculatorapp.calculator.presentation.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.calculatorapp.R
import com.example.calculatorapp.calculator.presentation.state.CalculatorState
import com.example.calculatorapp.calculator.presentation.viewmodel.CalculatorViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorActivity : AppCompatActivity() {

    private val viewModel: CalculatorViewModel by viewModel()

    private lateinit var toolbar: Toolbar
    private lateinit var inputEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        setupViews()
        setupToolbar()
        observeState()
    }

    private fun setupViews() {
        toolbar = findViewById(R.id.toolbar)
        inputEditText = findViewById(R.id.inputEditText)
        resultTextView = findViewById(R.id.resultTextView)
        calculateButton = findViewById(R.id.calculateButton)

        calculateButton.setOnClickListener {
            viewModel.evaluateExpression(inputEditText.text.toString())
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Калькулятор" // или на английском
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is CalculatorState.Idle -> {
                            // Ничего не делаем
                        }
                        is CalculatorState.Loading -> {
                            resultTextView.text = "Вычисляю..."
                        }
                        is CalculatorState.Success -> {
                            resultTextView.text = state.result
                        }
                        is CalculatorState.Error -> {
                            resultTextView.text = "Ошибка"
                            Toast.makeText(this@CalculatorActivity, state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}