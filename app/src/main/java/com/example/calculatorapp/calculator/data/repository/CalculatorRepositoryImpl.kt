package com.example.calculatorapp.calculator.data.repository

import com.example.calculatorapp.calculator.domain.repository.CalculatorRepository
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorRepositoryImpl : CalculatorRepository {
    override fun evaluateExpression(expression: String): Result<Double> {
        return try {
            val result = ExpressionBuilder(expression).build().evaluate()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}