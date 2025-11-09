package ru.otus.arch.basicauth.state

import ru.otus.arch.basicauth.data.BasicAuthGesture
import ru.otus.arch.basicauth.data.BasicAuthUiState
import kotlin.test.Test

internal class LoginStateTest : BaseStateTest() {

    fun createState() = LoginState(
        context,
        null,
        sessionManager
    )

    @Test
    fun rendersEmptyFormOnStart() = test {
        createState().start(stateMachine)

        verify(exhaustive = false, inOrder = true) {
            stateMachine.setUiState(isEqual(
                BasicAuthUiState.Login(
                    username = "",
                    password = "",
                    isEnabled = false,
                    error = null
                )
            ))
        }
    }

    @Test
    fun changesUserName() = test {
        val name = "user"

        val state = createState()
        state.start(stateMachine)
        state.process(BasicAuthGesture.UsernameChanged(name))

        verify(exhaustive = false, inOrder = true) {
            stateMachine.setUiState(isEqual(
                BasicAuthUiState.Login(
                    username = "",
                    password = "",
                    isEnabled = false,
                    error = null
                )
            ))
            stateMachine.setUiState(isEqual(
                BasicAuthUiState.Login(
                    username = name,
                    password = "",
                    isEnabled = false,
                    error = null
                )
            ))
        }
    }

    @Test
    fun changesPassword() = test {
        val password = "password"

        val state = createState()
        state.start(stateMachine)
        state.process(BasicAuthGesture.PasswordChanged(password))

        verify(exhaustive = false, inOrder = true) {
            stateMachine.setUiState(isEqual(
                BasicAuthUiState.Login(
                    username = "",
                    password = "",
                    isEnabled = false,
                    error = null
                )
            ))
            stateMachine.setUiState(isEqual(
                BasicAuthUiState.Login(
                    username = "",
                    password = password,
                    isEnabled = false,
                    error = null
                )
            ))
        }
    }

    @Test
    fun enablesLoginOnValidInput() = test {
        val name = "user"
        val password = "password"

        val state = createState()
        state.start(stateMachine)
        state.process(BasicAuthGesture.UsernameChanged(name))
        state.process(BasicAuthGesture.PasswordChanged(password))

        verify(exhaustive = false, inOrder = true) {
            stateMachine.setUiState(isEqual(
                BasicAuthUiState.Login(
                    username = "",
                    password = "",
                    isEnabled = false,
                    error = null
                )
            ))
            stateMachine.setUiState(isEqual(
                BasicAuthUiState.Login(
                    username = name,
                    password = "",
                    isEnabled = false,
                    error = null
                )
            ))
            stateMachine.setUiState(isEqual(
                BasicAuthUiState.Login(
                    username = name,
                    password = password,
                    isEnabled = true,
                    error = null
                )
            ))
        }
    }

    @Test
    fun logsIn() = test {
        val name = "user"
        val password = "password"

        everySuspending { sessionManager.login(isAny(), isAny()) } returns Unit

        val state = createState()
        state.start(stateMachine)
        state.process(BasicAuthGesture.UsernameChanged(name))
        state.process(BasicAuthGesture.PasswordChanged(password))
        state.process(BasicAuthGesture.Action)

        verifyWithSuspend(exhaustive = false, inOrder = true) {
            sessionManager.login(name, password)
            flowHost.onLogin()
        }
    }

    @Test
    fun doesNotLoginIfNotValid() = test {
        val name = "user"

        everySuspending { sessionManager.login(isAny(), isAny()) } runs {
            throw RuntimeException("Should not be called")
        }
        every { flowHost.onLogin() } runs {
            throw RuntimeException("Should not be called")
        }
        every { flowHost.onCancel() } runs {
            throw RuntimeException("Should not be called")
        }

        val state = createState()
        state.start(stateMachine)
        state.process(BasicAuthGesture.UsernameChanged(name))
        state.process(BasicAuthGesture.Action)
    }

    @Test
    fun cancelsOnBack() = test {
        val state = createState()
        state.start(stateMachine)
        state.process(BasicAuthGesture.Back)

        verify(exhaustive = false, inOrder = false) {
            flowHost.onCancel()
        }
    }
}