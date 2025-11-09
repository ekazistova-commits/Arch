package ru.otus.arch.basicauth.state

import ru.otus.arch.basicauth.api.BasicAuthFlowHost

internal interface BasicAuthContext {
    val factory: BasicStateFactory
    val flowHost: BasicAuthFlowHost
}