package kyu6

/*
* This is a series of Kotlin tricks.

In this Kata, you should make this compiles:

"this is a string, invoke it!"("wow, I am the argument!")
Whatever the argument is, just return the argument. There will be only one argument.

The example tests is the same as the final tests.
* */

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.test.assertEquals

class KotlinTricks0 {
    @Test
    fun testString() {
        assertEquals<String>("wow, I am the argument!", "this is a string, invoke it!"("wow, I am the argument!"))
        assertEquals<String>("s", "this is a string, invoke it!"("s"))
    }

    @Test
    fun testInt() {
        val r = Random(System.currentTimeMillis())
        (0..100).forEach { r.nextInt().let { assertEquals<Int>(it, "this is another string"(it)) } }
    }
//
//    @Test
//    fun testLong() {
//        val r = Random(System.currentTimeMillis())
//        (0..100).forEach { r.nextLong().let { assertEquals<Long>(it, "oh come on!"(it)) } }
//    }
//
//    @Test
//    fun testFile() {
//        val r = Random(System.currentTimeMillis())
//        (0..100).forEach { r.nextLong().let { assertEquals<File>(File(it.toString()), "oh come on!"(File(it.toString()))) } }
//    }
}

private operator fun Any.invoke(it: String): String {
    return it
}


//operator fun String.invoke(s: String): String {
//    return s
//}
