package ru.otus.arch.domain.navigation

/**
 * Common proxy state interface
 */
fun interface CommonFlowHost {
    /**
     * Called by child flow when it is finished
     */
    fun onComplete()
}