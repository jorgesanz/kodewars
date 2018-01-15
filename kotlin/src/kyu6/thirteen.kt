package kyu6

import org.junit.Assert.*
import org.junit.Test

fun thirt(n: Long): Long {

    var thirt = obtainReminder(n)
    var loop = true
    while(loop){
        val iteration = obtainReminder(thirt);
        if (iteration == thirt){
            loop = false;
        }
        else{
            thirt = iteration
        }
    }

    return thirt
}

fun obtainReminder(n: Long): Long {
    val reminders = arrayOf(1, 10, 9, 12, 3, 4)

    val stringNumber = n.toString()
    var sum: Long = 0
    repeat (stringNumber.length) {
        sum += (stringNumber[stringNumber.length - it -1]).toString().toInt()*reminders[it%reminders.size]
    }
    return sum
}


class ThirteenTest {
    @Test
    fun test1() {
        println("Fixed Tests: thirt")
        testing(thirt(8529), 79)
        testing(thirt(85299258), 31)

    }
    companion object {
        private fun testing(actual:Long, expected:Long) {
            assertEquals(expected, actual)
        }
    }
}