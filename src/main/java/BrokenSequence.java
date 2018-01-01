package added;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 15/02/17.
 *
 * You have a sequence of positive numbers starting with 1, but one number is missing!

 Find out the missing number; if the sequence is not broken, you should return 0. Each sequence always increments by 1.

 In short: an invalid sequence (a string with non numeric character) must return 1, an already complete (or empty) sequence must return 0; a broken sequence with more than one number missing should return the lowest missing number; otherwise return the missing number.

 Note that the input may be with random order.

 E.g.

 new BrokenSequence().findMissingNumber("1 3 2 5") // returns 4, because 4 is missing
 new BrokenSequence().findMissingNumber("1 2 3 4") // returns 0, because the sequence isn't broken
 new BrokenSequence().findMissingNumber("1 5") // returns 2, because the sequence is missing more than one number and 2 is the lowest between 2, 3 and 4
 new BrokenSequence().findMissingNumber("2 1 4 3 a") // returns 1, because it's an invalid sequence. in this case, it's invalid because contain a non numerical character

 *
 */
public class BrokenSequence {
    public int findMissingNumber(String sequence) {

        int missing = validateSequence(sequence);
        if(missing==1){
            return missing;
        }
        Set<Integer> numbersInSequence = extractNumbers(sequence);
        Integer firstMissing = 0;
        for(Integer i =1; i<=numbersInSequence.size(); i++){
            if(!sequence.contains(i.toString())){
                firstMissing = i;
                break;
            }
        }
        return firstMissing;
    }

    private Set<Integer> extractNumbers(String sequence) {
        Set numbers = new HashSet();
        String[] stringNumbers = sequence.split(" ");
        for(String stringNumber: stringNumbers){
            if(!stringNumber.equals("")){
                numbers.add(Integer.parseInt(stringNumber));
            }
        }
        return numbers;
    }

    private int validateSequence(String sequence) {
        sequence = sequence.replaceAll(" ","");
        for(int i=0;i<sequence.length();i++){
            Character character = sequence.charAt(i);
            if(!character.isDigit(character)){
                return 1;
            }
        }

        return 0;
    }


    @Test
    public void test0() {
        assertEquals("", 2, new BrokenSequence().findMissingNumber("1 5"));
    }



    @Test
    public void test1() {
        assertEquals("", 4, new BrokenSequence().findMissingNumber("1 2 3 5"));
    }

    @Test
    public void test2() {
        assertEquals("", 2, new BrokenSequence().findMissingNumber("1 3"));
    }

    @Test
    public void test3() {
        assertEquals("", 0, new BrokenSequence().findMissingNumber(""));
    }

    @Test
    public void test4() {
        assertEquals("", 1, new BrokenSequence().findMissingNumber("2 1 4 3 a"));
    }

    @Test
    public void test5() {
        assertEquals("", 0, new BrokenSequence().findMissingNumber("2 1 4 3 5"));
    }

}
