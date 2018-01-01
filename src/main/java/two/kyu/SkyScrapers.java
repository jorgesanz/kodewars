package two.kyu;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/*
*
* In a grid of 4 by 4 squares you want to place a skyscraper in each square with only some clues:

The height of the skyscrapers is between 1 and 4
No two skyscrapers in a row or column may have the same number of floors
A clue is the number of skyscrapers that you can see in a row or column from the outside
Higher skyscrapers block the view of lower skyscrapers located behind them

Can you write a program that can solve this puzzle?

*
* */
public class SkyScrapers {

    public static final int BOARD_SIZE = 2;

    static int[][] solvePuzzle(int[] clues) {
        int[][] solution = new int[BOARD_SIZE][BOARD_SIZE];
        return solvePuzzle(solution, 0);
    }

    private static int[][] solvePuzzle(int[][] solution, int position) {
        if(position==BOARD_SIZE*BOARD_SIZE)return solution;

        int i = position / BOARD_SIZE;
        int j = position % BOARD_SIZE;
        System.out.println(String.format("level %s %s",i,j));

        return solvePuzzle(solution, position + 1);


    }

    private static boolean isSolution(int[][] solution) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (solution[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static int clues[][] = {
            {2, 2, 1, 3,
                    2, 2, 3, 1,
                    1, 2, 2, 3,
                    3, 2, 1, 3},


            {0, 0, 1, 2,
                    0, 2, 0, 0,
                    0, 3, 0, 0,
                    0, 1, 0, 0}
    };

    private static int outcomes[][][] = {
            {{1, 3, 4, 2},
                    {4, 2, 1, 3},
                    {3, 4, 2, 1},
                    {2, 1, 3, 4}},


            {{2, 1, 4, 3},
                    {3, 4, 1, 2},
                    {4, 2, 3, 1},
                    {1, 3, 2, 4}}
    };

    @Test
    public void testSolvePuzzle1() {
        assertEquals(SkyScrapers.solvePuzzle(clues[0]), outcomes[0]);
    }

    @Test
    public void testSolvePuzzle2() {
        assertEquals(SkyScrapers.solvePuzzle(clues[1]), outcomes[1]);
    }
}
