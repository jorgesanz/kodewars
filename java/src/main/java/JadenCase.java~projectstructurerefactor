package added;

import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class JadenCase {
    public String toJadenCase(String phrase) {
        if (phrase == null || phrase.equals("")) return null;
        String jadenCased = "";
        for (int i = 0; i < phrase.length(); i++) {
            if (i == 0 || phrase.charAt(i - 1) == ' ') {
                jadenCased += (phrase.charAt(i) + "").toUpperCase();
            } else {
                jadenCased += phrase.charAt(i);
            }
        }
        return jadenCased;
    }


    @Test
    public void test() {
        JadenCase jadenCase = new JadenCase();
        assertEquals("toJadenCase doesn't return a valide JadenCase String! try again please :)", jadenCase.toJadenCase("most trees are blue"), "Most Trees Are Blue");
    }

    @Test
    public void testNullArg() {
        JadenCase jadenCase = new JadenCase();
        assertNull("Must return null when the arg is null", jadenCase.toJadenCase(null));
    }

    @Test
    public void testEmptyArg() {
        JadenCase jadenCase = new JadenCase();
        assertNull("Must return null when the arg is null", jadenCase.toJadenCase(""));
    }

}
