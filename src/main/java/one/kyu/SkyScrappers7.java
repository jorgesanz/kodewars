package one.kyu;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by jorge on 4/01/18.
 */
public class SkyScrappers7 {


    @Test
    public void solvePuzzle_Medium() {

        long initTime = System.currentTimeMillis();
        int[] clues = new int[]{ 7,0,0,0,2,2,3, 0,0,3,0,0,0,0, 3,0,3,0,0,5,0, 0,0,0,0,5,0,4 };

        int[][] expected = new int[][]{ new int[] { 1,5,6,7,4,3,2 },
                new int[] { 2,7,4,5,3,1,6 },
                new int[] { 3,4,5,6,7,2,1 },
                new int[] { 4,6,3,1,2,7,5 },
                new int[] { 5,3,1,2,6,4,7 },
                new int[] { 6,2,7,3,1,5,4 },
                new int[] { 7,1,2,4,5,6,3 }};

        int[][] actual = SkyScrappers6.solvePuzzle(clues);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("solution in %s milliseconds", endTime - initTime));
        assertArrayEquals(expected, actual);
    }


    @Test
    public void solvePuzzle_VeryHard3() {

        long initTime = System.currentTimeMillis();
        int[] clues = new int[]{ 0,2,3,0,2,0,0, 5,0,4,5,0,4,0, 0,4,2,0,0,0,6, 5,2,2,2,2,4,1 };      // NOTE: for a VERY HARD puzzle, replace the last 7 values with zeroes

        int[][] expected = new int[][]{ new int[] { 7,6,2,1,5,4,3 },
                new int[] { 1,3,5,4,2,7,6 },
                new int[] { 6,5,4,7,3,2,1 },
                new int[] { 5,1,7,6,4,3,2 },
                new int[] { 4,2,1,3,7,6,5 },
                new int[] { 3,7,6,2,1,5,4 },
                new int[] { 2,4,3,5,6,1,7 }};

        int[][] actual = SkyScrappers6.solvePuzzle(clues);

        long endTime = System.currentTimeMillis();
        System.out.println(String.format("solution in %s milliseconds", endTime - initTime));
        assertArrayEquals(expected, actual);
    }

    @Test
    public void solvePuzzle_VeryHard4() {

        long initTime = System.currentTimeMillis();
        int[] clues = new int[]{ 0,2,3,0,2,0,0, 5,0,4,5,0,4,0, 0,4,2,0,0,0,6, 0,0,0,0,0,0,0 };      // NOTE: for a VERY HARD puzzle, replace the last 7 values with zeroes

        int[][] expected = new int[][]{ new int[] { 7,6,2,1,5,4,3 },
                new int[] { 1,3,5,4,2,7,6 },
                new int[] { 6,5,4,7,3,2,1 },
                new int[] { 5,1,7,6,4,3,2 },
                new int[] { 4,2,1,3,7,6,5 },
                new int[] { 3,7,6,2,1,5,4 },
                new int[] { 2,4,3,5,6,1,7 }};

        int[][] actual = SkyScrappers6.solvePuzzle(clues);

        long endTime = System.currentTimeMillis();
        System.out.println(String.format("solution in %s milliseconds", endTime - initTime));
        assertArrayEquals(expected, actual);
    }
}
