import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 28/02/17.
 */
public class Opstrings1 {

    public static String rot(String strng) {
        List<String> matrix = getMatrix(strng);
        String rotatedString = "";
        for(int i = matrix.size()-1; i>= 0; i--){
            for(int j = matrix.size()-1; j>= 0; j--){
                rotatedString += matrix.get(i).charAt(j);
            }
            if(i>0){
                rotatedString+="\n";
            }
        }
        return rotatedString;
    }

    private static ArrayList<String> getMatrix(String strng) {
        return new ArrayList<>(Arrays.asList(strng.split("\n")));
    }

    public static String selfieAndRot(String strng) {
        String selfieAndRotatedString = "";
        List<String> matrix = getMatrix(strng);
        List<String> rotatedMatrix = getMatrix(rot(strng));
        String dots = getDots(rotatedMatrix.size());
        for(int i = 0; i< matrix.size(); i++){
            selfieAndRotatedString +=  matrix.get(i);
            selfieAndRotatedString += dots;
            selfieAndRotatedString += "\n";
        }
        for(int i = 0; i< rotatedMatrix.size(); i++){
            selfieAndRotatedString += dots;
            selfieAndRotatedString +=  rotatedMatrix.get(i);
            if(i!=rotatedMatrix.size()-1){
                selfieAndRotatedString += "\n";
            }
        }

        return selfieAndRotatedString;
    }

    private static String getDots(int size) {
        String dots = "";
        for(int i = 0 ; i < size ; i++){
            dots += ".";
        }
        return dots;
    }

    public static String oper(UnaryOperator<String> operator, String s) {
        try {
            return operator.apply(s);
        } catch (Exception e) {
            return null;
        }
    }

    private static void testing(String actual, String expected) {
        assertEquals(expected, actual);
    }
    @Test
    public void test() {
        System.out.println("Fixed Tests rot");
        testing(Opstrings1.oper(Opstrings1::rot, "fijuoo\nCqYVct\nDrPmMJ\nerfpBA\nkWjFUG\nCVUfyL"),
                "LyfUVC\nGUFjWk\nABpfre\nJMmPrD\ntcVYqC\nooujif");
        testing(Opstrings1.oper(Opstrings1::rot, "rkKv\ncofM\nzXkh\nflCB"),
                "BClf\nhkXz\nMfoc\nvKkr");

        System.out.println("Fixed Tests selfieAndRot");
        testing(Opstrings1.oper(Opstrings1::selfieAndRot, "xZBV\njsbS\nJcpN\nfVnP"),
                "xZBV....\njsbS....\nJcpN....\nfVnP....\n....PnVf\n....NpcJ\n....Sbsj\n....VBZx");
        testing(Opstrings1.oper(Opstrings1::selfieAndRot, "uLcq\nJkuL\nYirX\nnwMB"),
                "uLcq....\nJkuL....\nYirX....\nnwMB....\n....BMwn\n....XriY\n....LukJ\n....qcLu");
    }



}
