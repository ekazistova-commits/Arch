package ru.otus.arch.basicauth.data

import kotlin.js.JsExport

@JsExport
sealed class BasicAuthUiState {
    data class Login(
        val username: String,
        val password: String,
        val isEnabled: Boolean,
        val error: Throwable?
    ) : BasicAuthUiState()

    data class ForgotPassword(
        val username: String,
        val password: String
    ) : BasicAuthUiState()
}