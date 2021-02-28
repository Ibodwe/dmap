package com.example.dmap

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val test ="서울 중랑구 면목동 339-50"

        val t = test.split(" ")

        t.forEach {
            println(t)
        }

    }
}