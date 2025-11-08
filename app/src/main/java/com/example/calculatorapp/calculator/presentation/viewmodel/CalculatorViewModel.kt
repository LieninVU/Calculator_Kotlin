package com.example.calculatorapp.calculator.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculatorapp.calculator.domain.usecase.EvaluateExpressionUseCase
import com.example.calculatorapp.calculator.presentation.state.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val evaluateExpressionUseCase: EvaluateExpressionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CalculatorState>(CalculatorState.Idle)
    val state: StateFlow<CalculatorState> = _state

    fun evaluateExpression(expression: String) {
        if (expression.isBlank()) return
        _state.value = CalculatorState.Loading
        viewModelScope.launch {
            evaluateExpressionUseCase(expression)
                .onSuccess { result ->
                    _state.value = CalculatorState.Success(result.toString())
                }
                .onFailure { exception ->
                    _state.value = CalculatorState.Error(exception.message ?: "Error occurred")
                }
        }
    }
}