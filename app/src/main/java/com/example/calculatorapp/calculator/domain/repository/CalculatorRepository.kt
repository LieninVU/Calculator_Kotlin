package com.example.calculatorapp.calculator.domain.repository

interface CalculatorRepository {
    fun evaluateExpression(expression: String): Result<Double>
}