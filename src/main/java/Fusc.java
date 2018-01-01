package added;

import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 1/08/17.
 *
 * his Kata is a continuation of Part 1. The fusc function is defined recursively as follows:

 fusc(0) = 0
 fusc(1) = 1
 fusc(2n) = fusc(n)
 fusc(2n + 1) = fusc(n) + fusc(n + 1)
 Your job is to produce the code for the fusc function. In this kata, your function will be tested with large values of n (more than 1000 bits), so you should be concerned about stack overflow and timeouts.

 NOTE: In JavaScript and PHP, your function will be tested with n up to 52 bits. This will still require a non-naive solution. This will also overflow 32-bit operators, but it will be integer arithmetic.

 Hint: Define F(n, a, b) = a * fusc(n) + b * fusc(n + 1) and provide a recursive definition of F without referencing fusc.


 */
public class Fusc {

    public static final BigInteger TWO = new BigInteger("2");
    public static Map<BigInteger,BigInteger> results = new HashMap<>();

    public static BigInteger fusc (BigInteger n) {

        if(results.containsKey(n)){
            return results.get(n);
        }
        if(n.equals(BigInteger.ZERO)){
            return BigInteger.ZERO;
        }
        else if(n.equals(BigInteger.ONE)){
            return BigInteger.ONE;
        }
        else if(isOdd(n)){
            BigInteger fuscN = n.subtract(BigInteger.ONE).divide(TWO);
            BigInteger fuscNsum1 = fuscN.add(BigInteger.ONE);
            BigInteger result = fusc(fuscN).add(fusc(fuscNsum1));
            results.put(n,result);
            return result;
        }
        else{
            BigInteger result = fusc(n.divide(TWO));
            results.put(n,result);
            return result;
        }
    }

    private static boolean isOdd(BigInteger n) {
        return n.mod(TWO).equals(BigInteger.ONE);
    }

    @Test
    public void testSomething()
    {
        BigInteger twoPThous = BigInteger.valueOf(2).pow(1000);
//        assertEquals(BigInteger.ZERO, Fusc.fusc(BigInteger.ZERO));
//        assertEquals(BigInteger.ONE, Fusc.fusc(BigInteger.ONE));
//        assertEquals(BigInteger.valueOf(1001), Fusc.fusc(twoPThous.add(BigInteger.ONE)));
//        assertEquals(BigInteger.valueOf(1000), Fusc.fusc(twoPThous.subtract(BigInteger.ONE)));
//        assertEquals(BigInteger.valueOf(2996), Fusc.fusc(twoPThous.add(BigInteger.valueOf(5))));
//        assertEquals(BigInteger.valueOf(7973), Fusc.fusc(twoPThous.add(BigInteger.valueOf(21))));

        long init = System.currentTimeMillis();
        Fusc.fusc(new BigInteger("7173828741239926231326044738610103742676607462018501471848156635183125134212229247653583714546124638897164871925194925134182991729903458072119196058176124835610222052014830410825244238175128551427391350466539203775976467699354468212617001946518457889853738399106850026809959171020911420200687677425687"));
        Fusc.fusc(new BigInteger("85"));
        long end = System.currentTimeMillis();
        System.out.println(end-init+" milisegundos");
    }
}