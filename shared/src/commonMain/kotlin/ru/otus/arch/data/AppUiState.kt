package ru.otus.arch.data

import ru.otus.arch.basicauth.data.BasicAuthUiState
import kotlin.js.JsExport

@JsExport
sealed class AppUiState {
    object Welcome : AppUiState()
    object Loading : AppUiState()
    data class Error(val error: Throwable, val canRetry: Boolean) : AppUiState()
    object Terminated: AppUiState()

    data class UserList(
        val users: List<User>,
        val loggedIn: Boolean
    ) : AppUiState()
    data class UserProfile(val profile: Profile) : AppUiState()

    data class AddUserForm(
        val name: String,
        val age: Int,
        val interests: String,
        val addEnabled: Boolean
    ) : AppUiState()

    data class Auth(val child: BasicAuthUiState) : AppUiState()
}
