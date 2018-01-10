package added;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

/**
 * Created by jorge on 28/07/17.
 *
 * Description:

 Given an array of numbers, determine whether the sum of all of the numbers is odd or even.

 Give your answer in string format as 'odd' or 'even'.

 If the input array is empty consider it as: [0] (array with a zero).

 Example:

 oddOrEven([2, 5, 34, 6]) returns "odd".
 */
public class OddOrEven {
    public static String oddOrEven (int[] array) {

        List<Integer> numbers = new ArrayList();
        for (int intNumber :array){
            numbers.add(new Integer (intNumber));
        }
        return(isOdd(numbers)?"odd":"even");
    }

    private static boolean isOdd(List<Integer> numbers) {
        Integer first = numbers.get(0);
        if(numbers.size()==1){
            return isOdd(first);
        }else{
            numbers.remove(0);
            return sumIsOdd(isOdd(first), isOdd(numbers));
        }
    }

    private static boolean sumIsOdd(boolean number1isOdd, boolean number2isOdd) {
        return((number1isOdd && !number2isOdd) || (!number1isOdd && number2isOdd));
    }

    private static boolean isOdd(int integer) {
        return (integer%2==1);
    }



    /*
    * LambdaSolution
    * */

    static String oddOrEvenLambda(final int[] array) {
        return stream(array).sum() % 2 == 0 ? "even" : "odd";
    }

    @Test
    public void exampleTest() {
        assertEquals("odd", OddOrEven.oddOrEven(new int[] {2, 5, 34, 6}));
    }
}