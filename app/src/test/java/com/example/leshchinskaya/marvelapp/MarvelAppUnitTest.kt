package com.example.leshchinskaya.marvelapp

import com.example.leshchinskaya.marvelapp.Lab
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MarvelAppUnitTest {
    @Test
    fun sumOfElements_isCorrect() {
        var list = mutableListOf<Int?>(5, 7 ,3 ,4 , 1)
        assertEquals(20, Lab.sumOfElements(list))
    }

    @Test(expected = KotlinNullPointerException::class)
    fun sumOfElements_isThrowException() {
        var list = mutableListOf<Int?>(5, 7 ,3 ,4 , null)
        Lab.sumOfElements(list)
    }

}
