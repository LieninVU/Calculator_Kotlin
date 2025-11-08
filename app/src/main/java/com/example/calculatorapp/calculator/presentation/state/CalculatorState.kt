package com.example.calculatorapp.calculator.presentation.state

sealed class CalculatorState {
    object Idle : CalculatorState()
    object Loading : CalculatorState()
    data class Success(val result: String) : CalculatorState()
    data class Error(val message: String) : CalculatorState()
}