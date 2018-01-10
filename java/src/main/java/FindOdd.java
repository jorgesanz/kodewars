package added;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 15/02/17.
 * Given an array, find the int that appears an odd number of times.

 There will always be only one integer that appears an odd number of times.
 *
 */
public class FindOdd {

    public static int findIt(int[] A) {
        Map<Integer,Integer> apparitions = new HashMap<>();
        for(Integer number: A){
            if(apparitions.containsKey(number)){
                apparitions.put(number,apparitions.get(number)+1);
            }else{
                apparitions.put(number,1);
            }
        }
        for(Integer apparition:apparitions.keySet()){
            if(apparitions.get(apparition)%2==1){
                return apparition;
            }
        }
        return 0;
    }

    @Test
    public void findTest() {
        assertEquals(5, FindOdd.findIt(new int[]{20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5}));
        assertEquals(-1, FindOdd.findIt(new int[]{1,1,2,-2,5,2,4,4,-1,-2,5}));
        assertEquals(5, FindOdd.findIt(new int[]{20,1,1,2,2,3,3,5,5,4,20,4,5}));
        assertEquals(10, FindOdd.findIt(new int[]{10}));
        assertEquals(10, FindOdd.findIt(new int[]{1,1,1,1,1,1,10,1,1,1,1}));
        assertEquals(1, FindOdd.findIt(new int[]{5,4,3,2,1,5,4,3,2,10,10}));
    }
}
