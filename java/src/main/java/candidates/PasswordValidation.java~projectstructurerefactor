package candidates;

import java.util.regex.Pattern;

/**
 * Created by jorge on 30/03/17.
 *
 * Implement the strongPassword function, which accepts a password and returns true if the password is strong and false if it's not.

 A password is strong if it satisfies the following rules:

 It is at least 12 characters long.
 It contains at least one uppercase letter, at least one lowercase letter and at least one digit.
 Letters should be split by digits into at least two groups (like "Strong1Password").
 For example, "strong1password" is not a strong password because it does not contain any uppercase letters.
 *
 */
public class PasswordValidation {

    public static Boolean strongPassword(String password) {
        if (password.length() < 12)
            return false;

        if (!Pattern.matches(".*[a-z]+.*", password) || !Pattern.matches(".*[A-Z]+.*", password) || !Pattern.matches(".*[0-9]+.*", password))
            return false;

        if (!Pattern.matches(".*[a-zA-Z]+[0-9]+[a-zA-Z]+.*", password))
            return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.println(strongPassword("Strong1Password"));
    }
}