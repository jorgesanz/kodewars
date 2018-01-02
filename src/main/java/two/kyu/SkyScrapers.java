package two.kyu;

import org.junit.Test;

import java.util.HashSet;
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
        int[][] solution = new int[BOARD_SIZE][BOARD_SIZE];
        return solvePuzzle(solution, 0);
    }

    private static int[][] solvePuzzle(int[][] currentBoard, int position) {

        if(position!=BOARD_SIZE*BOARD_SIZE){
            int i = position / BOARD_SIZE;
            int j = position % BOARD_SIZE;
            //System.out.println(String.format("level %s %s",i,j));
            for (int k = 1; k<=BOARD_SIZE ; k++){
                int[][] updatedBoard = copyBoard(currentBoard);
                updatedBoard[i][j]=k;
                //PODA
                if(isRealCandidate(updatedBoard)){
                    int[][] candidate = solvePuzzle(updatedBoard, position + 1);
                    if(isSolution(candidate)){
                        return candidate;
                    }
                }
            }

        }
        else{
            //printSolution(currentBoard);
        }
        return currentBoard;
    }

    private static boolean isRealCandidate(int[][] updatedBoard) {
        return (validateRow(updatedBoard) && validateColumn(updatedBoard) );
    }

    private static boolean validateColumn(int[][] updatedBoard) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Set heightFound = new HashSet();
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(updatedBoard[j][i]!=0 && heightFound.contains(updatedBoard[j][i])){
                    return false;
                }
                else{
                    heightFound.add(updatedBoard[j][i]);
                }
            }
        }
        return true;
    }

    private static boolean validateRow(int[][] updatedBoard) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Set heightFound = new HashSet();
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(updatedBoard[i][j] !=0 && heightFound.contains(updatedBoard[i][j])){
                    return false;
                }
                else{
                    heightFound.add(updatedBoard[i][j]);
                }
            }
        }
        return true;
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
        if(sameFlatHeight(solution)) return false;
        return true;
    }

    private static boolean sameFlatHeight(int[][] solution) {
        if (validateRowHeight(solution)) return true;
        if (validateColumnHeight(solution)) return true;
        return false;
    }

    private static boolean validateColumnHeight(int[][] solution) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Set heightFound = new HashSet();
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(heightFound.contains(solution[j][i])){
                    return true;
                }
                else{
                    heightFound.add(solution[j][i]);
                }
            }
        }
        return false;
    }

    private static boolean validateRowHeight(int[][] solution) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Set heightFound = new HashSet();
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(heightFound.contains(solution[i][j])){
                    return true;
                }
                else{
                    heightFound.add(solution[i][j]);
                }
            }
        }
        return false;
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
        long initTime = System.currentTimeMillis();

        int[][] solution = SkyScrapers.solvePuzzle(clues[0]);

        long endTime = System.currentTimeMillis();

        System.out.println(String.format("final solution found in %s milliseconds",endTime-initTime));

        printSolution(solution);
        assertEquals(solution, outcomes[0]);
    }

    @Test
    public void testSolvePuzzle2() {
        assertEquals(SkyScrapers.solvePuzzle(clues[1]), outcomes[1]);
    }
}
