package com.example.calculatorapp.calculator.domain.usecase

import com.example.calculatorapp.calculator.domain.repository.CalculatorRepository

class EvaluateExpressionUseCase(
    private val repository: CalculatorRepository
) {
    suspend operator fun invoke(expression: String): Result<Double> {
        return repository.evaluateExpression(expression)
    }
}