package added;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 14/02/17.
 * In this kata you get the start number and the end number of a region and should return the count of all numbers except numbers with a 5 in it. The start and the end number are both inclusive!

 Examples:

 1,9 -> 1,2,3,4,6,7,8,9 -> Result 8
 4,17 -> 4,6,7,8,9,10,11,12,13,14,16,17 -> Result 12
 The result may contain fives. ;-)
 The start number will always be smaller than the end number. Both numbers can be also negative!

 I'm very curious for your solutions and the way you solve it. Maybe someone of you will find an easy pure mathematics solution.

 Have fun coding it and please don't forget to vote and rank this kata! :-)

 I have also created other katas. Take a look if you enjoyed this kata!
 *
 */
public class DontGiveMeFive {

    public static int dontGiveMeFive(int start, int end) {
        int count = 0;
        for(int i = start ; i<=end; i++){
            if(!hasFives(i)) count ++;
        }
        return count;
    }

    private static boolean hasFives(int i) {
        String stringNumber = ""+i;
        return stringNumber.contains("5");
    }


    public static int dontGiveMeFiveStream(int start, int end){
        return  (int) (long) obtainSequence(start, end).stream().map(elem->""+elem).filter(elem -> !elem.contains("5")).count();
    }

    private static List<Integer> obtainSequence(int start, int end) {
        List<Integer> seguence = new ArrayList<>();
        for(int i = start; i <= end; i++){
            seguence.add(i);
        }
        return seguence;
    }


    @Test
    public void exampleTests() {
        assertEquals(8, DontGiveMeFive.dontGiveMeFiveStream(1, 9));
        assertEquals(12, DontGiveMeFive.dontGiveMeFiveStream(4, 17));
    }

}



