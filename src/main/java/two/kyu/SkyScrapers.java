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

    public static final int BOARD_SIZE = 3;

    static int[][] solvePuzzle(int[] clues) {
        int[][] solution = new int[BOARD_SIZE][BOARD_SIZE];
        return solvePuzzle(solution, 0);
    }

    private static int[][] solvePuzzle(int[][] currentBoard, int position) {
        if(position!=BOARD_SIZE*BOARD_SIZE){
            int i = position / BOARD_SIZE;
            int j = position % BOARD_SIZE;
            //System.out.println(String.format("level %s %s",i,j));
            for (int k = 0; k<BOARD_SIZE ; k++){
                int[][] solution = copyBoard(currentBoard);
                solution[i][j]=k;
                solvePuzzle(solution, position + 1);
            }

        }
        else{
            printSolution(currentBoard);
        }
        return currentBoard;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE ; i++){
            for(int j = 0; j < BOARD_SIZE ; j++){
                copy[i][j]= board[i][j];
            }
        }
        return copy;
    }

    private static void printSolution(int[][] solution) {
        System.out.println("New Solution");
        for(int i = 0; i < BOARD_SIZE ; i++){
            for(int j = 0; j < BOARD_SIZE ; j++){
                System.out.println(String.format("%s %s: %s",i,j,solution[i][j]));
            }
        }
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
