package candidates;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 1/08/17.
 */
public class RangeExtraction {

    public static String rangeExtraction(int[] arr) {
        SortedSet<Integer> sortedSet = sort(arr);
        List<String> elements = addNumbersToString(sortedSet);
        return concat(elements);
    }

    private static String concat(List<String> elements) {
        String result = "";
        for(String element : elements){
            if(result.equals("")){
                result+=element;
            }else{
                result+=","+element;
            }
        }
        return result;
    }

    private static List<String> addNumbersToString( SortedSet<Integer> sortedSet) {
        List<String> elements = new ArrayList<>();
        if (sortedSet.size() == 0){
            return elements;
        }else if(sortedSet.size() == 1){
            elements.add(sortedSet.first().toString());
            return elements;
        }else{
            Integer first = sortedSet.first();
            sortedSet.remove(first);
            Integer lastInRange = sortedSet.first();
            if(!lastInRange.equals(first+1)){
                elements.add(first.toString());
                elements.addAll(addNumbersToString(sortedSet));
                return elements;
            }
            sortedSet.remove(lastInRange);
            while(sortedSet.size()>0 && sortedSet.first().equals(lastInRange+1)){
                lastInRange = sortedSet.first();
                sortedSet.remove(lastInRange);
            }
            if(lastInRange.equals(first+1)){
                elements.add(first.toString());
                elements.add(lastInRange.toString());
                elements.addAll(addNumbersToString(sortedSet));
                return elements;
            }
            elements.add(first+"-"+lastInRange);
            elements.addAll(addNumbersToString(sortedSet));
            return elements;
        }
    }

    private static SortedSet<Integer> sort(int[] arr) {
        SortedSet<Integer> sortedSet = new TreeSet<>();
        for (int element: arr){
            sortedSet.add(element);
        }
        return sortedSet;
    }

    @Test
    public void test_BasicTests() {

        assertEquals("-6,-3-1,3-5,7-11,14,15,17-20", RangeExtraction.rangeExtraction(new int[] {-6,-3,-2,-1,0,1,3,4,5,7,8,9,10,11,14,15,17,18,19,20}));

        assertEquals("-3--1,2,10,15,16,18-20", RangeExtraction.rangeExtraction(new int[] {-3,-2,-1,2,10,15,16,18,19,20}));

        assertEquals("-3--1,2,10,15,16,18,19,25", RangeExtraction.rangeExtraction(new int[] {-3,-2,-1,2,10,15,16,18,19,25}));
    }
}
