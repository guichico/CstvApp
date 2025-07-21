package com.codechallenge.test

import org.mockito.MockitoAnnotations

abstract class AbstractTest {
    private var mocksObject: AutoCloseable? = null

    fun openMocks(clazz: Any) {
        mocksObject = MockitoAnnotations.openMocks(clazz)
    }

    fun closeMocks() {
        mocksObject?.close()
        mocksObject = null
    }
}