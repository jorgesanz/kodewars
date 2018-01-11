package added;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SudokuValidator {
    public static boolean check(int[][] sudoku) {
        if(!checkZeros(sudoku)) return false;
        if(!checkLines(sudoku)) return false;
        if(!checkColumns(sudoku)) return false;
        return true;
    }

    private static boolean checkColumns(int[][] sudoku) {
        for(int i = 0; i<9; i++){
            Set<Integer> numbers = new HashSet<>();
            for(int j = 0; j<9; j++){
                numbers.add(sudoku[j][i]);
            }
            if(numbers.size()!=9){
                return false;
            }
        }
        return true;
    }

    private static boolean checkLines(int[][] sudoku) {
        for(int i = 0; i<9; i++){
            Set<Integer> numbers = new HashSet<>();
            for(int j = 0; j<9; j++){
                numbers.add(sudoku[i][j]);
            }
            if(numbers.size()!=9){
                return false;
            }
        }
        return true;
    }

    private static boolean checkZeros(int[][] sudoku) {
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                if(sudoku[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }


    @Test
    public void exampleTest() {
        int[][] sudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        assertEquals(true, SudokuValidator.check(sudoku));

        sudoku[4][4] = 0;

        assertEquals(false, SudokuValidator.check(sudoku));
    }
}
