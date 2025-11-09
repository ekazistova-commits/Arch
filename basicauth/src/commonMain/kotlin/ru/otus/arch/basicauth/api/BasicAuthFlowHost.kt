package ru.otus.arch.basicauth.api

/**
 * Hosting proxy for basic login flow.
 */
interface BasicAuthFlowHost {
    /**
     * Login succeeded
     */
    fun onLogin()

    /**
     * Login failed
     */
    fun onCancel()
}
