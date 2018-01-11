package added;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jorge on 14/02/17.
 */
public class Persist {


    public static int persistence(long n) {
        long persistenceLong = persistenceLong(n);
        return (int) persistenceLong;
    }

    private static long persistenceLong(long n) {
        Collection<Long> digits = splitDigits(n);
        if (digits.size() == 1) {
            return 0;
        } else {
            long digitsMultiplication = multiplication(digits);
            return (1 + Persist.persistenceLong(digitsMultiplication));
        }
    }

    private static Long multiplication(Collection<Long> digits) {
        Long multiplication = 1l;
        for (Long digit : digits) {
            multiplication = multiplication * digit;
        }
        return multiplication;
    }

    public static Collection<Long> splitDigits(long n) {
        List<Long> digits = new ArrayList<>();
        if (n < 10) {
            digits.add(n);
            return digits;
        } else {
            long currentDigit = n % 10;
            digits.add(currentDigit);
            long mod = n / 10;
            digits.addAll(splitDigits(mod));
            return digits;
        }
    }

    public static void main(String[] args) {
        System.out.println("****** Basic Tests ******");
        Persist.persistence(39);
        Persist.persistence(4);
        Persist.persistence(25);
        Persist.persistence(999);
    }


}
