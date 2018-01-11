package added;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 28/02/17.
 * Write function scramble(str1,str2) that returns true if a portion of str1 characters can be rearranged to match str2, otherwise returns false.

 For example:
 str1 is 'rkqodlw' and str2 is 'world' the output should return true.
 str1 is 'cedewaraaossoqqyt' and str2 is 'codewars' should return true.
 str1 is 'katas' and str2 is 'steak' should return false.

 Only lower case letters will be used (a-z). No punctuation or digits will be included.
 Performance needs to be considered
 *
 */
public class Scramblies {
    public static boolean scramble(String str1, String str2) {
        Map<Character,Integer> str1Occurrences =findOccurrences(str1);
        Map<Character,Integer> str2Occurrences =findOccurrences(str2);
        for(Character character :str2Occurrences.keySet()){
            if(!str1Occurrences.containsKey(character) || str1Occurrences.get(character)<str2Occurrences.get(character)){
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> findOccurrences(String str) {
        Map<Character,Integer> characterOccurrences = new HashMap<>();
        for(int i = 0; i< str.length(); i++){
            if(characterOccurrences.containsKey(str.charAt(i))){
                characterOccurrences.put(str.charAt(i),characterOccurrences.get(str.charAt(i))+1);
            }
            else{
                characterOccurrences.put(str.charAt(i),1);
            }
        }
        return characterOccurrences;
    }

    private static void testing(boolean actual, boolean expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests scramble");
        testing(Scramblies.scramble("rkqodlw","world"), true);
        testing(Scramblies.scramble("cedewaraaossoqqyt","codewars"),true);
        testing(Scramblies.scramble("katas","steak"),false);
        testing(Scramblies.scramble("scriptjavx","javascript"),false);
        testing(Scramblies.scramble("scriptingjava","javascript"),true);
        testing(Scramblies.scramble("scriptsjava","javascripts"),true);
        testing(Scramblies.scramble("javscripts","javascript"),false);
        testing(Scramblies.scramble("aabbcamaomsccdd","commas"),true);
        testing(Scramblies.scramble("commas","commas"),true);
        testing(Scramblies.scramble("sammoc","commas"),true);
    }

}
