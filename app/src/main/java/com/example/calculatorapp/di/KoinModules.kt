package com.example.calculatorapp.di

import com.example.calculatorapp.calculator.data.repository.CalculatorRepositoryImpl
import com.example.calculatorapp.calculator.domain.repository.CalculatorRepository
import com.example.calculatorapp.calculator.domain.usecase.EvaluateExpressionUseCase
import com.example.calculatorapp.calculator.presentation.viewmodel.CalculatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CalculatorRepository> { CalculatorRepositoryImpl() }
    factory { EvaluateExpressionUseCase(get()) }
    viewModel { CalculatorViewModel(get()) }
}