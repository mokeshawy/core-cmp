package com.shared.core.di

import com.shared.core.coroutines_scope.CoroutinesScopeModule
import com.shared.core.websocket.di.websocketModule
import org.koin.dsl.module


val coreModule = module {
    includes(
        websocketModule,
        CoroutinesScopeModule
    )
}