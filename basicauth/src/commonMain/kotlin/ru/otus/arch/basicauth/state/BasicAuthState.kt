package ru.otus.arch.basicauth.state

import com.motorro.commonstatemachine.coroutines.CoroutineState
import io.github.aakira.napier.Napier
import ru.otus.arch.basicauth.data.BasicAuthGesture
import ru.otus.arch.basicauth.data.BasicAuthUiState

internal abstract class BasicAuthState(context: BasicAuthContext) : CoroutineState<BasicAuthGesture, BasicAuthUiState>(), BasicAuthContext by context {
    override fun doProcess(gesture: BasicAuthGesture) {
        Napier.w { "Unsupported gesture: $gesture" }
    }
}