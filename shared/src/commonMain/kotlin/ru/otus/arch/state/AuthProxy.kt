package ru.otus.arch.state

import com.motorro.commonstatemachine.CommonMachineState
import com.motorro.commonstatemachine.ProxyMachineState
import ru.otus.arch.basicauth.api.BasicAuthApi
import ru.otus.arch.basicauth.api.BasicAuthFlowHost
import ru.otus.arch.basicauth.data.BasicAuthGesture
import ru.otus.arch.basicauth.data.BasicAuthUiState
import ru.otus.arch.data.AppGesture
import ru.otus.arch.data.AppUiState

internal class AuthProxy(
    private val context: AppContext,
    private val error: Throwable?,
    private val onLogin: AppStateFactory.() -> AppState,
    private val onCancel: AppStateFactory.() -> AppState,
    private val api: (BasicAuthFlowHost) -> BasicAuthApi
) : ProxyMachineState<AppGesture, AppUiState, BasicAuthGesture, BasicAuthUiState>(BasicAuthApi.getDefaultUiState()) {

    private val flowHost = object : BasicAuthFlowHost {
        override fun onLogin() {
            setMachineState(onLogin(context.factory))
        }

        override fun onCancel() {
            setMachineState(onCancel(context.factory))
        }
    }

    override fun init(): CommonMachineState<BasicAuthGesture, BasicAuthUiState> = api(flowHost).start(error)

    override fun mapGesture(parent: AppGesture): BasicAuthGesture? = when(parent) {
        is AppGesture.Auth -> parent.child
        is AppGesture.Back -> BasicAuthGesture.Back
        else -> null
    }

    override fun mapUiState(child: BasicAuthUiState): AppUiState = AppUiState.Auth(child)

    class Factory(private val api: (BasicAuthFlowHost) -> BasicAuthApi) {
    // class Factory(private val ff: FF) {
        operator fun invoke(
            context: AppContext,
            error: Throwable?,
            onLogin: AppStateFactory.() -> AppState,
            onCancel: AppStateFactory.() -> AppState
        ) = AuthProxy(
            context,
            error,
            onLogin,
            onCancel,
            api
        )
    }
}