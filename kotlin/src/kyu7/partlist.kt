package kyu7

/**
 * Created by jorge on 9/01/18.
 */
import org.junit.Assert.*
import java.util.Arrays
import org.junit.Test


fun partlist(arr:Array<String>): Array<Array<String>?> {

    var result = arrayOfNulls<Array<String>>(arr.size-1)

    for (i in 1 until arr.size) {
        var first = ""
        var second = ""
        for(j in 0 until i){
            if(!first.equals("")){
                first += " "
            }
            first += arr[j]
        }
        for(j in i until arr.size){
            if(!second.equals("")){
                second += " "
            }
            second += arr[j]
        }
        val tuple = arrayOf(first,second)
        result.set(i-1,tuple)
    }
    return result
}

class PartlistTest {
    private fun testing(actual:String, expected:String) {
        assertEquals(expected, actual)
    }
    @Test
    fun test() {
        println("Fixed Tests partlist")
        var s1 = arrayOf<String>("cdIw", "tzIy", "xDu", "rThG")
        var a = "[[cdIw, tzIy xDu rThG], [cdIw tzIy, xDu rThG], [cdIw tzIy xDu, rThG]]"
        testing(Arrays.deepToString(partlist(s1)), a)
        s1 = arrayOf<String>("I", "wish", "I", "hadn't", "come")
        a = "[[I, wish I hadn't come], [I wish, I hadn't come], [I wish I, hadn't come], [I wish I hadn't, come]]"
        testing(Arrays.deepToString(partlist(s1)), a)

    }
}