package one.kyu;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;

public class SkyScrappers6 {


    public static final int BOARD_SIZE = 6;

    static int[][] solvePuzzle(int[] clues){


        List<Node> nodes = new ArrayList<>();

        nodes.add(new Node(new int[BOARD_SIZE][BOARD_SIZE],0, 0));
        while(!nodes.isEmpty()){
            Node firstNode = nodes.get(0);
            nodes.remove(firstNode);
            if(isSolution(clues, firstNode.getBoard())){
                return firstNode.getBoard();
            }
            if(!isFinalNode(firstNode.getLevel())){
                nodes.addAll(expand(firstNode));
                nodes.sort(Comparator.comparing(Node::getScore).reversed());
            }
        }
        throw new RuntimeException("No solution found");
    }

    private static Collection<? extends Node> expand(Node firstNode) {
        List<Node> nodes = new ArrayList<>();
        int i = firstNode.getLevel() / BOARD_SIZE;
        int j = firstNode.getLevel() % BOARD_SIZE;
        for(int k=1;k<=BOARD_SIZE;k++){
            int[][] updatedBoard = copyBoard(firstNode.getBoard());
            updatedBoard[i][j] = k;
            if (validateHeights(updatedBoard)) {
                nodes.add(new Node(updatedBoard,firstNode.getLevel()+1, firstNode.getLevel()+1));
            }
        }
        return nodes;
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

                    if (isFinalNode(position) && isSolution(clues, updatedBoard)) {
                        success = true;
                        solutions.add(updatedBoard);
                    }
                }
            }
        }
    }

    private static boolean isFinalNode(int position) {
        if (position == BOARD_SIZE * BOARD_SIZE - 1){
            System.out.println("final node found");
        }
        return position == BOARD_SIZE * BOARD_SIZE - 1;
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


    private static boolean isSolution(int[] clues, int[][] solution) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!validateClueRow(i, clues, solution)) return false;
        }
        return true;
    }


    private static boolean validateClueRow(int i, int[] clues, int[][] solution) {
        switch (i) {
            case 0:
                if (!validateTopClues(clues, solution)) return false;
                break;
            case 1:
                if (!validateRightClues(clues, solution)) return false;
                break;
            case 2:
                if (!validateBottomClues(clues, solution)) return false;
                break;
            case 3:
                if (!validateLeftClues(clues, solution)) return false;
                break;
        }
        return true;
    }

    private static boolean validateLeftClues(int[] clues, int[][] solution) {
        int[] leftClues = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            leftClues[i] = clues[i + (3 * BOARD_SIZE)];
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            int clue = leftClues[i];
            if (clue != 0) {
                int[] skyScrapperLine = new int[BOARD_SIZE];
                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[BOARD_SIZE - i - 1][j];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != clue) return false;
            }
        }
        return true;
    }

    private static boolean validateBottomClues(int[] clues, int[][] solution) {
        int[] bottomClues = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            bottomClues[i] = clues[i + (2 * BOARD_SIZE)];
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            int clue = bottomClues[i];
            if (clue != 0) {
                int[] skyScrapperLine = new int[BOARD_SIZE];
                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[BOARD_SIZE - j - 1][BOARD_SIZE - i - 1];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != clue) return false;
            }
        }
        return true;
    }

    private static boolean validateRightClues(int[] clues, int[][] solution) {
        int[] rightClues = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            rightClues[i] = clues[i + BOARD_SIZE];
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            int clue = rightClues[i];
            if (clue != 0) {
                int[] skyScrapperLine = new int[BOARD_SIZE];
                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[i][BOARD_SIZE - 1 - j];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != clue) return false;
            }
        }

        return true;
    }

    private static boolean validateTopClues(int[] clues, int[][] solution) {
        int[] topClues = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            topClues[i] = clues[i];
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            int clue = topClues[i];
            if (clue != 0) {
                int[] skyScrapperLine = new int[BOARD_SIZE];
                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[j][i];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != clue) return false;
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

    public static class Node {
        private int level;
        private int[][] board;
        private Integer score;

        public Node(int[][] board, int level, int score) {
            this.board=board;
            this.level=level;
            this.score = score;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int[][] getBoard() {
            return board;
        }

        public void setBoard(int[][] board) {
            this.board = board;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }

    @Test
    public void solvePuzzle1()
    {
        long initTime = System.currentTimeMillis();
        int[] clues = new int[]{ 3, 2, 2, 3, 2, 1,
                1, 2, 3, 3, 2, 2,
                5, 1, 2, 2, 4, 3,
                3, 2, 1, 2, 2, 4};

        int[][] expected = new int[][]{new int []{ 2, 1, 4, 3, 5, 6},
                new int []{ 1, 6, 3, 2, 4, 5},
                new int []{ 4, 3, 6, 5, 1, 2},
                new int []{ 6, 5, 2, 1, 3, 4},
                new int []{ 5, 4, 1, 6, 2, 3},
                new int []{ 3, 2, 5, 4, 6, 1 }};

        int[][] actual = SkyScrappers6.solvePuzzle(clues);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("solution in %s milliseconds",endTime-initTime));
        assertArrayEquals(expected, actual);
    }

    @Test

    public void solvePuzzle2()
    {
        int[] clues = new int []{ 0, 0, 0, 2, 2, 0,
                0, 0, 0, 6, 3, 0,
                0, 4, 0, 0, 0, 0,
                4, 4, 0, 3, 0, 0};

        int[][] expected = new int[][]{new int[]{ 5, 6, 1, 4, 3, 2 },
                new int[]{ 4, 1, 3, 2, 6, 5 },
                new int[]{ 2, 3, 6, 1, 5, 4 },
                new int[]{ 6, 5, 4, 3, 2, 1 },
                new int[]{ 1, 2, 5, 6, 4, 3 },
                new int[]{ 3, 4, 2, 5, 1, 6 }};

        int[][] actual = SkyScrappers6.solvePuzzle(clues);
        assertArrayEquals(expected, actual);
    }

    @Test

    public void solvePuzzle3()
    {
        int[] clues = new int[] { 0, 3, 0, 5, 3, 4,
                0, 0, 0, 0, 0, 1,
                0, 3, 0, 3, 2, 3,
                3, 2, 0, 3, 1, 0};

        int[][] expected = new int[][]{new int[]{ 5, 2, 6, 1, 4, 3 },
                new int[]{ 6, 4, 3, 2, 5, 1 },
                new int[]{ 3, 1, 5, 4, 6, 2 },
                new int[]{ 2, 6, 1, 5, 3, 4 },
                new int[]{ 4, 3, 2, 6, 1, 5 },
                new int[]{ 1, 5, 4, 3, 2, 6 }};

        int[][] actual = SkyScrappers6.solvePuzzle(clues);
        assertArrayEquals(expected, actual);
    }
}
