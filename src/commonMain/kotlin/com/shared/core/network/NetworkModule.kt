package com.shared.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class NetworkModule() {

    val api: HttpClient = HttpClient {

        expectSuccess = true

        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        defaultRequest {
            url("https://fooddelivery-5c175-default-rtdb.firebaseio.com/")
            header("Authorization", "Bearer")
        }

        val loggingPlugin = createClientPlugin("LoggingPlugin") {
            onRequest { request, _ ->
                println("Sending request: ${request.url}")
            }
            onResponse { response ->
                println("Received response: ${response.bodyAsText()}")
            }
        }

        install(loggingPlugin)
    }
}

