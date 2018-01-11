package added;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorge on 28/02/17.
 * The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence in an array or list of integers:

 Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
 // should be 6: {4, -1, 2, 1}
 Easy case is when the list is made up of only positive numbers and the maximum sum is the sum of the whole array. If the list is made up of only negative numbers, return 0 instead.

 Empty list is considered to have zero greatest sum. Note that the empty list or array is also a valid sublist/subarray.

 */
public class MaxSubarraySum {
    public static int sequence(int[] arr) {
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = i ; j < arr.length; j++ ){
                int result = sumAll(i,j,arr);
                if(result>max){
                    max = result;
                }
            }
        }
        return max;
    }

    private static int sumAll(int i, int j, int[] arr) {
        int sum = 0;
        for(int k = i; k <= j; k++){
            sum+=arr[k];
        }
        return sum;
    }

    @Test
    public void testEmptyArray() throws Exception {
        assertEquals("Empty arrays should have a max of 0", 0, MaxSubarraySum.sequence(new int[]{}));
    }
    @Test
    public void testExampleArray() throws Exception {
        assertEquals("Example array should have a max of 6", 6, MaxSubarraySum.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
