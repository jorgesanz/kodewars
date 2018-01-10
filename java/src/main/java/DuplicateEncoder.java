package added;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 15/02/17.
 *
 * The goal of this exercise is to convert a string to a new string where each character in the new string is '(' if that character appears only once in the original string, or ')' if that character appears more than once in the original string. Ignore capitalization when determining if a character is a duplicate.

 Examples:

 "din" => "((("

 "recede" => "()()()"

 "Success" => ")())())"

 "(( @" => "))(("

 */
public class DuplicateEncoder {
    static String encode(String word){
        word = word.toLowerCase();
        Map<Character,Character> translation = new HashMap<>();
        for(int i=0;i<word.length();i++){
            Character character = word.charAt(i);
            if(translation.containsKey(character)){
                translation.put(character,')');
            }else{
                translation.put(character,'(');
            }
        }
        String translatedWord = "";
        for(int i=0;i<word.length();i++){
            Character character = word.charAt(i);
            translatedWord+=translation.get(character);
        }
        return translatedWord;
    }

    @Test
    public void test() {
        assertEquals(")()())()(()()(",
                DuplicateEncoder.encode("Prespecialized"));
        assertEquals("))))())))",DuplicateEncoder.encode("   ()(   "));
    }
}
