package added;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 14/02/17.
 */
public class Factorial {
    public int factorial(int n) {
        validateValue(n);
        return recursiveFactorial(n);
    }

    private int recursiveFactorial(int n) {
        return (n==0?1:n*recursiveFactorial(n-1));
    }

    private void validateValue(int n) {
        if(n<0 || n>12) throw new IllegalArgumentException();
    }

    private Factorial fact;

    @Before
    public void initFactorial() {
        fact = new Factorial();
    }

    @After
    public void afterFactorial() {
        fact = null;
    }

    @Test
    public void test_factorial0() {
        assertEquals(1, fact.factorial(0));
    }

    @Test
    public void test_factorial3() {
        assertEquals(6, fact.factorial(3));
    }

    @Test
    public void test_factorial5() {
        assertEquals(120, fact.factorial(5));
    }
}
