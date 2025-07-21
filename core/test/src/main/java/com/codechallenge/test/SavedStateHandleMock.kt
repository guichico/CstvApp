package com.codechallenge.test

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic

class SavedStateHandleMock() {

    companion object {
        private val savedStateHandleMock: SavedStateHandle = mockk()

        fun <T : Any> create(route: T): SavedStateHandle {
            mockkStatic("androidx.navigation.SavedStateHandleKt")
            every { savedStateHandleMock.toRoute<Any>(any(), any()) }
                .returns(route)

            return savedStateHandleMock
        }
    }
}