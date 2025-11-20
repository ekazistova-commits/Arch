package ru.otus.arch

import androidx.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        App()
    }
}