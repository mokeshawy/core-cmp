package com.shared.core.websocket.provide_http_client

import io.ktor.client.HttpClient

expect fun provideHttpClient(): HttpClient
