package ru.otus.arch.basicauth.data

import kotlin.js.JsExport

@JsExport
sealed class BasicAuthGesture {
    data object Back : BasicAuthGesture()
    data object Action : BasicAuthGesture()
    data class UsernameChanged(val username: String) : BasicAuthGesture()
    data class PasswordChanged(val password: String) : BasicAuthGesture()
    data object ForgotPassword : BasicAuthGesture()
}
