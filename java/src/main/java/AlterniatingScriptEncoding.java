import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 15/02/17.
 */
public class AlterniatingScriptEncoding {
    public static String encrypt(final String text, final int n) {
        if(n<=0){
            return text;
        }
        else{
            return encrypt(encrypt(text),n-1);
        }
    }

    private static String encrypt(String text) {
        String firstPart="";
        String secondPart = "";
        for(int i=0;i<text.length();i++){
            Character currentChar = text.charAt(i);
            if(i % 2 == 1){
                firstPart+=currentChar;
            }
            else{
                secondPart += currentChar;
            }
        }
        return firstPart+secondPart;
    }

    public static String decrypt(final String encryptedText, final int n) {
        if(n<=0){
            return encryptedText;
        }
        else{
            return decrypt(decrypt(encryptedText),n-1);
        }
    }

    private static String decrypt(String encryptedText) {
        int splitCharPosition = (encryptedText.length()/2);
        String secondPart = encryptedText.substring(0,splitCharPosition);
        String firstPart = encryptedText.substring(splitCharPosition);
        String decripted = "";
        for(int i = 0; i<= splitCharPosition; i++){
            String firstPartChar = (i<firstPart.length()?((Character)firstPart.charAt(i)).toString():"");
            String secondPartChar = (i<secondPart.length()?((Character)secondPart.charAt(i)).toString():"");
            decripted+= firstPartChar+""+secondPartChar;
        }
        return decripted;
    }

    @Test
    public void testEncrypt() {
        // assertEquals("expected", "actual");
        assertEquals("This is a test!", AlterniatingScriptEncoding.encrypt("This is a test!", 0));
        assertEquals("hsi  etTi sats!", AlterniatingScriptEncoding.encrypt("This is a test!", 1));
        assertEquals("s eT ashi tist!", AlterniatingScriptEncoding.encrypt("This is a test!", 2));
        assertEquals(" Tah itse sits!", AlterniatingScriptEncoding.encrypt("This is a test!", 3));
        assertEquals("This is a test!", AlterniatingScriptEncoding.encrypt("This is a test!", 4));
        assertEquals("This is a test!", AlterniatingScriptEncoding.encrypt("This is a test!", -1));
        assertEquals("hskt svr neetn!Ti aai eyitrsig", AlterniatingScriptEncoding.encrypt("This kata is very interesting!", 1));
    }

    @Test
    public void testDecrypt() {
        // assertEquals("expected", "actual");
        assertEquals("This is a test!", AlterniatingScriptEncoding.decrypt("This is a test!", 0));
        assertEquals("This is a test!", AlterniatingScriptEncoding.decrypt("hsi  etTi sats!", 1));
        assertEquals("This is a test!", AlterniatingScriptEncoding.decrypt("s eT ashi tist!", 2));
        assertEquals("This is a test!", AlterniatingScriptEncoding.decrypt(" Tah itse sits!", 3));
        assertEquals("This is a test!", AlterniatingScriptEncoding.decrypt("This is a test!", 4));
        assertEquals("This is a test!", AlterniatingScriptEncoding.decrypt("This is a test!", -1));
        assertEquals("This kata is very interesting!", AlterniatingScriptEncoding.decrypt("hskt svr neetn!Ti aai eyitrsig", 1));
    }

    @Test
    public void testNullOrEmpty() {
        // assertEquals("expected", "actual");
        assertEquals("", AlterniatingScriptEncoding.encrypt("", 0));
        assertEquals("", AlterniatingScriptEncoding.decrypt("", 0));
        assertEquals(null, AlterniatingScriptEncoding.encrypt(null, 0));
        assertEquals(null, AlterniatingScriptEncoding.decrypt(null, 0));
    }
}
