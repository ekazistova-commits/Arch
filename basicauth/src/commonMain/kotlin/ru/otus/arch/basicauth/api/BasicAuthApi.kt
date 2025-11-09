package ru.otus.arch.basicauth.api

import com.motorro.commonstatemachine.CommonMachineState
import ru.otus.arch.basicauth.data.BasicAuthGesture
import ru.otus.arch.basicauth.data.BasicAuthUiState

/**
 * Module API
 */
interface BasicAuthApi {
    /**
     * Starts login flow
     * @param error Error to be displayed
     * @return Login flow state
     */
    fun start(error: Throwable?): CommonMachineState<BasicAuthGesture, BasicAuthUiState>

    companion object {
        /**
         * Default screen state
         */
        fun getDefaultUiState(): BasicAuthUiState = BasicAuthUiState.Login(
            username = "",
            password = "",
            isEnabled = false,
            error = null
        )
    }
}