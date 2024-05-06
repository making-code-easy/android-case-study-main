package com.target.targetcasestudy.presentation.common

sealed class DealsUiState<out T : Any> {
    data class Success<out T : Any>(val data: T) : DealsUiState<T>()
    data class Fail(val message: String) : DealsUiState<Nothing>()
    object Loading : DealsUiState<Nothing>()
}