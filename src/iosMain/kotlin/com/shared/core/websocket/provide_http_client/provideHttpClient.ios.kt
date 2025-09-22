package com.shared.core.websocket.provide_http_client

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.websocket.WebSockets

actual fun provideHttpClient(): HttpClient = HttpClient(Darwin) {
    install(WebSockets)
}