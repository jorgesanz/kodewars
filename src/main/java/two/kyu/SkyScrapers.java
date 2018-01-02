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
        int[][] solution = new int[BOARD_SIZE][BOARD_SIZE];
        List<int[][]> candidates = new ArrayList<>();
        obtainCandidates(solution, 0, candidates);
        List<int[][]> solutions = obtainSolutions(clues,candidates);
        solution = solutions.get(0);
        return solution;
    }

    private static List<int[][]> obtainSolutions(int[] clues, List<int[][]> candidates) {
        List<int[][]> solutions = new ArrayList<>();
        for(int[][] candidate : candidates){
            if (isSolution(clues,candidate)){
                solutions.add(candidate);
            }
        }
        return solutions;
    }

    private static void obtainCandidates(int[][] currentBoard, int position, List<int[][]> solutions) {

        if(position!=BOARD_SIZE*BOARD_SIZE){
            int i = position / BOARD_SIZE;
            int j = position % BOARD_SIZE;
            for (int k = 1; k<=BOARD_SIZE ; k++){
                int[][] updatedBoard = copyBoard(currentBoard);
                updatedBoard[i][j]=k;
                //PODA
                if(isRealCandidate(updatedBoard)){
                    obtainCandidates(updatedBoard, position + 1, solutions);
//                    if(isSolution(clues ,candidate)){
                    if (position == BOARD_SIZE * BOARD_SIZE -1){
                        solutions.add(updatedBoard);
                    }
                }
            }

        }
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


    private static boolean isSolution(int[] clues, int[][] solution) {
        for(int i =0; i<BOARD_SIZE; i++){
            if (!validateClueRow(i,clues,solution)) return false;
        }
        return true;
    }


    private static boolean validateClueRow(int i, int[] clues, int[][] solution) {
        switch (i){
            case 0:
                if(!validateTopClues(clues,solution)) return false;
                break;
            case 1:
                if(!validateRightClues(clues,solution)) return false;
                break;
            case 2:
                if(!validateBottomClues(clues,solution)) return false;
                break;
            case 3:
                if(!validateLeftClues(clues,solution)) return false;
                break;
        }
        return true;
    }

    private static boolean validateLeftClues(int[] clues, int[][] solution) {
        int[] leftClues = new int[BOARD_SIZE];
        for (int i=0;i<BOARD_SIZE;i++){
            leftClues[i]= clues[i+(3*BOARD_SIZE)];
        }
        for(int i=0;i<BOARD_SIZE;i++){
            int clue = leftClues[i];
            if(clue!=0){
                int[] skyScrapperLine = new int[BOARD_SIZE];
                for(int j=0; j<BOARD_SIZE;j++){
                    skyScrapperLine[j]=solution[BOARD_SIZE-i-1][j];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings!=clue) return false;
            }
        }
        return true;
    }

    private static boolean validateBottomClues(int[] clues, int[][] solution) {
        int[] bottomClues = new int[BOARD_SIZE];
        for (int i=0;i<BOARD_SIZE;i++){
            bottomClues[i]= clues[i+(2*BOARD_SIZE)];
        }
        for(int i = 0; i<BOARD_SIZE; i++){
            int clue = bottomClues[i];
            if(clue !=0){
                int[] skyScrapperLine = new int[BOARD_SIZE];
                for(int j=0;j<BOARD_SIZE;j++){
                    skyScrapperLine[j]=solution[BOARD_SIZE-j-1][BOARD_SIZE-i-1];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings!=clue) return false;
            }
        }
        return true;
    }

    private static boolean validateRightClues(int[] clues, int[][] solution) {
        int[] rightClues = new int[BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++){
            rightClues[i] = clues[i+BOARD_SIZE];
        }
        for(int i=0; i<BOARD_SIZE; i++){
            int clue = rightClues[i];
            if (clue!=0){
                int[] skyScrapperLine = new int[BOARD_SIZE];
                for (int j=0;j<BOARD_SIZE;j++){
                    skyScrapperLine[j]=solution[i][BOARD_SIZE-1-j];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings!=clue) return false;
            }
        }

        return true;
    }

    private static boolean validateTopClues(int[] clues, int[][] solution) {
        int[] topClues = new int[BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++){
            topClues[i] = clues[i];
        }
        for(int i=0; i<BOARD_SIZE; i++){
            int clue = topClues[i];
            if (clue!=0){
                int[] skyScrapperLine = new int[BOARD_SIZE];
                for (int j=0;j<BOARD_SIZE;j++){
                    skyScrapperLine[j]=solution[j][i];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings!=clue) return false;
            }
        }

        return true;
    }

    private static int visibleBuildings(int[] skyScrapperLine) {
        int maxHeight = skyScrapperLine[0];
        int visibleBuildings = 1;
        for(int i= 1; i< BOARD_SIZE; i++){
            if(skyScrapperLine[i]>maxHeight){
                maxHeight = skyScrapperLine[i];
                visibleBuildings ++;
            }
        }
        return visibleBuildings;
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
//        long initTime = System.currentTimeMillis();
//
//        int[][] solution = SkyScrapers.solvePuzzle(clues[0]);
//
//        long endTime = System.currentTimeMillis();
//
//        System.out.println(String.format("final solution found in %s milliseconds",endTime-initTime));

//        printSolution(solution);
        assertEquals(SkyScrapers.solvePuzzle(clues[0]), outcomes[0]);
    }

    @Test
    public void testSolvePuzzle2() {
        assertEquals(SkyScrapers.solvePuzzle(clues[1]), outcomes[1]);
    }
}
