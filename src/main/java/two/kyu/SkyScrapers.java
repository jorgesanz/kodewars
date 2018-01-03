package two.kyu;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static final int BOARD_SIZE = 4;

    static int[][] solvePuzzle(int[] clues) {
        int[][] initialBoard = new int[BOARD_SIZE][BOARD_SIZE];
        List<int[][]> solutions = new ArrayList<>();
        obtainSolution(clues, initialBoard, 0, solutions, false);
        return solutions.get(0);
    }


    private static void obtainSolution(int[] clues, int[][] currentBoard, int position, List<int[][]> solutions, boolean success) {

        if (position != BOARD_SIZE * BOARD_SIZE && !success) {
            int i = position / BOARD_SIZE;
            int j = position % BOARD_SIZE;
            for (int k = 1; k <= BOARD_SIZE; k++) {
                int[][] updatedBoard = copyBoard(currentBoard);
                updatedBoard[i][j] = k;
                if (validateHeights(updatedBoard)) {
                    obtainSolution(clues, updatedBoard, position + 1, solutions, success);
                    if (position == BOARD_SIZE * BOARD_SIZE - 1 && isSolution(clues, updatedBoard)) {
                        success = true;
                        solutions.add(updatedBoard);
                    }
                }
            }
        }
    }


    private static boolean validateHeights(int[][] updatedBoard) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Set columnHeights = new HashSet();
            Set rowHeights = new HashSet();
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((updatedBoard[i][j] != 0 && rowHeights.contains(updatedBoard[i][j])) || (updatedBoard[j][i] != 0 && columnHeights.contains(updatedBoard[j][i]))) {
                    return false;
                } else {
                    columnHeights.add(updatedBoard[j][i]);
                    rowHeights.add(updatedBoard[i][j]);
                }
            }
        }
        return true;
    }


    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }


    private static boolean isSolution(int[] clues, int[][] solution){
        int[] leftClues = new int[BOARD_SIZE];
        int[] bottomClues = new int[BOARD_SIZE];
        int[] rightClues = new int[BOARD_SIZE];
        int[] topClues = new int[BOARD_SIZE];


        for (int i = 0; i < BOARD_SIZE; i++) {
            leftClues[i] = clues[i + (3 * BOARD_SIZE)];
            bottomClues[i] = clues[i + (2 * BOARD_SIZE)];
            rightClues[i] = clues[i + BOARD_SIZE];
            topClues[i] = clues[i];
        }
        for (int i = 0; i < BOARD_SIZE; i++) {

            int leftClue = leftClues[i];
            int bottomClue = bottomClues[i];
            int rightClue = rightClues[i];
            int topClue = topClues[i];

            int[] skyScrapperLine = new int[BOARD_SIZE];

            if (leftClue != 0) {

                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[BOARD_SIZE - i - 1][j];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != leftClue) return false;
            }
            if (bottomClue != 0) {

                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[BOARD_SIZE - j - 1][BOARD_SIZE - i - 1];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != bottomClue) return false;
            }
            if (rightClue != 0) {

                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[i][BOARD_SIZE - 1 - j];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != rightClue) return false;
            }
            if (topClue != 0) {

                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[j][i];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != topClue) return false;
            }

        }
        return true;
    }

    private static int visibleBuildings(int[] skyScrapperLine) {
        int maxHeight = skyScrapperLine[0];
        int visibleBuildings = 1;
        for (int i = 1; i < BOARD_SIZE; i++) {
            if (skyScrapperLine[i] > maxHeight) {
                maxHeight = skyScrapperLine[i];
                visibleBuildings++;
            }
        }
        return visibleBuildings;
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
        int[][] solution = SkyScrapers.solvePuzzle(clues[0]);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                assertEquals(solution[i][j], outcomes[0][i][j]);
            }
        }
    }

    @Test
    public void testSolvePuzzle2() {

        int[][] solution = SkyScrapers.solvePuzzle(clues[1]);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                assertEquals(solution[i][j], outcomes[1][i][j]);
            }
        }
    }
}
