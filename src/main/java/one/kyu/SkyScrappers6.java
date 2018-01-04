package one.kyu;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertArrayEquals;


public class SkyScrappers6 {

    public static final int BOARD_SIZE = 7;

    static int[][] solvePuzzle(int[] clues) {
        long initTime = System.currentTimeMillis();

        List<Clue> clueCombinations = obtainClueCombinations(clues);
        System.out.println(String.format("clue combinations obtained in %s millis",System.currentTimeMillis()-initTime));
        clueCombinations.sort(Comparator.comparing(Clue::getCombinationsSize));
        List<int[][]> candidates = obtainCandidates(clueCombinations,0, new int[BOARD_SIZE][BOARD_SIZE]);
        System.out.println(String.format("candidates obtained in %s millis",System.currentTimeMillis()-initTime));
        List<Node> nodes = candidates.stream().map(candidate -> new Node(candidate, 0, getScore(clues,candidate))).collect(toList());

        int maxScore = 0;
        while (!nodes.isEmpty()) {
            Node node = nodes.get(0);
            nodes.remove(node);
            if (isFinalNode(node.getLevel())) {
                if (isSolution(clues, node.getBoard())) {
                    System.out.println(String.format("solution obtained in %s millis",System.currentTimeMillis()-initTime));
                    return node.getBoard();
                }
            } else {
                nodes.addAll(expand(node, clues));
                nodes.sort(Comparator.comparing(Node::getScore).reversed());
                if (nodes.get(0) != null && nodes.get(0).getScore() > maxScore) {
                    maxScore = nodes.get(0).getScore();
                }
            }
        }
        throw new RuntimeException("No solution found");
    }

    /**
     *  obtains all the candidates combinating a clue with the combination of the previous clues
     * @param clueCombinations the whole set of combination of clues
     * @param cluePosition the position of the clue is going to be processed
     * @param currentCandidate the best combination of clues at the moment
     * @return the list of candidates
     */
    private static List<int[][]> obtainCandidates(final List<Clue> clueCombinations, int cluePosition, int[][] currentCandidate) {

        List<int[][]> currentCandidates = new ArrayList<>();

        if (cluePosition == clueCombinations.size()){
            currentCandidates.add(currentCandidate);
            return currentCandidates;
        }
        else{
            Clue clue = clueCombinations.get(cluePosition);
            if(clue.getCombinationsSize()==0){
                currentCandidates.addAll(obtainCandidates(clueCombinations, cluePosition +1, currentCandidate));
            }
            for(int[][] clueCombination: clue.getCombinations()){
                if(matches(currentCandidate,clueCombination)){
                    int[][] mix = match(currentCandidate,clueCombination);
                    if(validateHeights(mix)){
                        currentCandidates.addAll(obtainCandidates(clueCombinations, cluePosition +1, mix));
                    }
                }
            }
            return currentCandidates;
        }
    }

    /**
     *  mixes a pair of matrices
     * @param currentCandidate
     * @param clueCombination
     * @return
     */
    private static int[][] match(int[][] currentCandidate, int[][] clueCombination) {

        int[][] mix = new int[BOARD_SIZE][BOARD_SIZE];

        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                if( currentCandidate[i][j] != clueCombination[i][j] && currentCandidate[i][j]!=0){
                    mix[i][j] = currentCandidate[i][j];
                }
                else{
                    mix[i][j] = clueCombination[i][j];
                }
                mix[i][j] = ( currentCandidate[i][j] != clueCombination[i][j] && currentCandidate[i][j]!=0 ? currentCandidate[i][j] : clueCombination[i][j]);
            }
        }
        return mix;
    }

    /**
     * decides if a pair of matrices can be mixed. 2 matrices can be mixed if don't contain a different number in the same position (except 0's)
     * @param currentCandidate
     * @param clueCombination
     * @return
     */
    private static boolean matches(int[][] currentCandidate, int[][] clueCombination) {
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                if(currentCandidate[i][j] !=0 && clueCombination[i][j] != 0 && currentCandidate[i][j] != clueCombination[i][j] ){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * obtain all the combinations of matris for clue in a determinate position
     * @param clue
     * @param matrixPosition
     * @return
     */
    private static List<int[][]> obtainAllMatrix(int clue, int matrixPosition){
        return obtainAllCombinations().parallelStream()
                .filter(elem -> visibleBuildings(elem) == clue)
                .map(row -> obtainMatrix(row,matrixPosition))
                .collect(toList());
    }

    /**
     * given an array of clues, obtains a list of all the combinations of matrices for this clues
     * @param clues
     * @return
     */
    private static List<Clue> obtainClueCombinations(int[] clues) {
        List<Clue> clueCombinations = IntStream.range(0,clues.length)
                .boxed().map(position->obtainAllMatrix(clues[position],position)).map(clueOptions -> new Clue(clueOptions))
                .collect(toList());
        return clueCombinations;
    }

    public static class Clue{
        private List<int[][]> combinations;

        public Clue(List<int[][]> combinations){
            this.combinations=combinations;
        }

        public List<int[][]> getCombinations() {
            return combinations;
        }

        public int getCombinationsSize(){
            return (combinations!=null?combinations.size():0);
        }
    }

    /**
     * given a skyscrappers line, and the position of the clue, creates the matrix of this clue
     * @param skyScrappers
     * @param position
     * @return
     */

    private static int[][] obtainMatrix(int[] skyScrappers, int position) {
        int[][] matrix = new int[BOARD_SIZE][BOARD_SIZE];
        switch (position/BOARD_SIZE){
            case 0:
                for(int i=0;i<BOARD_SIZE;i++){
                    matrix[i][position % BOARD_SIZE] = skyScrappers[i];
                }
                break;
            case 1:
                for(int i=0;i<BOARD_SIZE;i++){
                    matrix[position % BOARD_SIZE][BOARD_SIZE -1 -i] = skyScrappers[i];
                }
                break;
            case 2:
                for(int i=0;i<BOARD_SIZE;i++){
                    matrix[BOARD_SIZE -1 -i][BOARD_SIZE - (position % BOARD_SIZE) -1]  = skyScrappers[i];
                }
                break;
            case 3:
                matrix[BOARD_SIZE -1 - position%BOARD_SIZE]=skyScrappers;
                break;
        }
        return matrix;
    }

    /**
     * obtains all the combinations of BOARD_SIZE elements without duplicates
     * @return
     */
    private static List<int[]> obtainAllCombinations(){
        Set<Integer> options = new HashSet<>();
        for (int i=1;i<=BOARD_SIZE;i++){
            options.add(i);
        }
        return obtainAllCombinations(options,new int[BOARD_SIZE]);
    }

    /**
     * recursive method to obtain all combination of BOARD_SIZE elements without duplicates
     * @param options
     * @param currentCombination
     * @return
     */
    private static List<int[]> obtainAllCombinations(Set<Integer> options, int[] currentCombination) {
        List<int[]> elements = new ArrayList<>();
        if(options.isEmpty()){
            elements.add(currentCombination);
            return elements;
        }
        else{

            for (Integer option:options){
                    int[] copy = copyLine(currentCombination);
                    Set<Integer> newOptions = new HashSet<>(options);
                    newOptions.remove(option);
                    copy[BOARD_SIZE-options.size()]=option;
                    elements.addAll(obtainAllCombinations(newOptions,copy));
            }
            return elements;
        }
    }

    /**
     * copies a line into a new one
     * @param original
     * @return
     */
    private static int[] copyLine(int[] original) {
        int[] copy = new int[original.length];
        for (int i=0; i<BOARD_SIZE; i++){
            copy[i]=original[i];
        }
        return copy;
    }

    /**
     * Expand all the posiblities of a node with recursion
     * @param firstNode
     * @param clues
     * @return
     */
    private static Collection<? extends Node> expand(Node firstNode, int[] clues) {
        List<Node> nodes = new ArrayList<>();
        int i = firstNode.getLevel() / BOARD_SIZE;
        int j = firstNode.getLevel() % BOARD_SIZE;
        if (firstNode.getBoard()[i][j] != 0) {
            firstNode.setLevel(firstNode.getLevel() + 1);
            firstNode.setScore(getScore(clues, firstNode.getBoard()));
            nodes.add(firstNode);
        } else {
            for (int k = 1; k <= BOARD_SIZE; k++) {
                int[][] updatedBoard = copyBoard(firstNode.getBoard());
                updatedBoard[i][j] = k;
                if (validateHeights(updatedBoard)) {
                    int score = getScore(clues, firstNode.getBoard());
                    nodes.add(new Node(updatedBoard, firstNode.getLevel() + 1, score));
                }
            }
        }
        return nodes;
    }

    /**
     * decides if the current node is a leaf of the tree
     * @param position
     * @return
     */
    private static boolean isFinalNode(int position) {
        return position == BOARD_SIZE * BOARD_SIZE;
    }

    /**
     * validates if the current board doesn't contains the same values in row or column
     * @param updatedBoard
     * @return
     */
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

    /**
     * copies a board into a new one
     * @param board
     * @return
     */
    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    /**
     * a board is a solution if none of the clues are broken
     * @param clues
     * @param solution
     * @return
     */
    private static boolean isSolution(int[] clues, int[][] solution) {
        BoardScore boardScore = processBoard(clues, solution);
        return boardScore.isSolution();
    }

    /**
     * obtains a score of the solution, to implement the priority queue
     * @param clues
     * @param solution
     * @return
     */
    private static int getScore(int[] clues, int[][] solution) {
        BoardScore boardScore = processBoard(clues, solution);
        return boardScore.getScore();
    }

    /**
     * process all the clues and obtains the score
     * @param clues
     * @param solution
     * @return
     */
    private static BoardScore processBoard(int[] clues, int[][] solution) {
        BoardScore boardScore = new BoardScore();

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
                if (visibleBuildings != leftClue) {
                    boardScore.setSolution(false);
                } else if (noZeros(skyScrapperLine)) {
                    boardScore.addPoints(1);
                }
            }
            if (bottomClue != 0) {

                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[BOARD_SIZE - j - 1][BOARD_SIZE - i - 1];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != bottomClue) {
                    boardScore.setSolution(false);
                } else if (noZeros(skyScrapperLine)) {
                    boardScore.addPoints(1);
                }
            }
            if (rightClue != 0) {

                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[i][BOARD_SIZE - 1 - j];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != rightClue) {
                    boardScore.setSolution(false);
                } else if (noZeros(skyScrapperLine)) {
                    boardScore.addPoints(1);
                }
            }
            if (topClue != 0) {

                for (int j = 0; j < BOARD_SIZE; j++) {
                    skyScrapperLine[j] = solution[j][i];
                }
                int visibleBuildings = visibleBuildings(skyScrapperLine);
                if (visibleBuildings != topClue) {
                    boardScore.setSolution(false);
                } else if (noZeros(skyScrapperLine)) {
                    boardScore.addPoints(1);
                }
            }
        }
        if (boardScore.isSolution() == null) {
            boardScore.setSolution(true);
        }
        return boardScore;
    }

    /**
     * check if the array contains 0's
     * @param skyScrapperLine
     * @return
     */
    private static boolean noZeros(int[] skyScrapperLine) {
        for (int value : skyScrapperLine) {
            if (value == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * calculates how many buildings are visible from a clue position
     * @param skyScrapperLine
     * @return
     */
    private static int visibleBuildings(int[] skyScrapperLine) {
        int maxHeight = skyScrapperLine[0];
        int visibleBuildings = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (skyScrapperLine[i] >= maxHeight) {
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
            this.board = board;
            this.level = level;
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

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }

    public static class BoardScore {
        private Boolean isSolution;
        private int score;

        public Boolean isSolution() {
            return isSolution;
        }

        public void setSolution(Boolean solution) {
            isSolution = solution;
        }

        public int getScore() {
            return score;
        }

        public void addPoints(int score) {
            this.score += score;
        }
    }

    @Test
    public void solvePuzzle1() {
        long initTime = System.currentTimeMillis();
        int[] clues = new int[]{3, 2, 2, 3, 2, 1,
                1, 2, 3, 3, 2, 2,
                5, 1, 2, 2, 4, 3,
                3, 2, 1, 2, 2, 4};//total 57

        int[][] expected = new int[][]{new int[]{2, 1, 4, 3, 5, 6},
                new int[]{1, 6, 3, 2, 4, 5},
                new int[]{4, 3, 6, 5, 1, 2},
                new int[]{6, 5, 2, 1, 3, 4},
                new int[]{5, 4, 1, 6, 2, 3},
                new int[]{3, 2, 5, 4, 6, 1}};

        int[][] actual = SkyScrappers6.solvePuzzle(clues);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("solution in %s milliseconds", endTime - initTime));
        assertArrayEquals(expected, actual);
    }

    @Test

    public void solvePuzzle2() {
        long initTime = System.currentTimeMillis();
        int[] clues = new int[]{0, 0, 0, 2, 2, 0,
                0, 0, 0, 6, 3, 0,
                0, 4, 0, 0, 0, 0,
                4, 4, 0, 3, 0, 0};//total 28

        int[][] expected = new int[][]{new int[]{5, 6, 1, 4, 3, 2},
                new int[]{4, 1, 3, 2, 6, 5},
                new int[]{2, 3, 6, 1, 5, 4},
                new int[]{6, 5, 4, 3, 2, 1},
                new int[]{1, 2, 5, 6, 4, 3},
                new int[]{3, 4, 2, 5, 1, 6}};

        int[][] actual = SkyScrappers6.solvePuzzle(clues);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("solution in %s milliseconds", endTime - initTime));
        assertArrayEquals(expected, actual);
    }

    @Test

    public void solvePuzzle3() {
        long initTime = System.currentTimeMillis();
        int[] clues = new int[]{0, 3, 0, 5, 3, 4,
                0, 0, 0, 0, 0, 1,
                0, 3, 0, 3, 2, 3,
                3, 2, 0, 3, 1, 0};// total 36

        int[][] expected = new int[][]{new int[]{5, 2, 6, 1, 4, 3},
                new int[]{6, 4, 3, 2, 5, 1},
                new int[]{3, 1, 5, 4, 6, 2},
                new int[]{2, 6, 1, 5, 3, 4},
                new int[]{4, 3, 2, 6, 1, 5},
                new int[]{1, 5, 4, 3, 2, 6}};

        int[][] actual = SkyScrappers6.solvePuzzle(clues);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("solution in %s milliseconds", endTime - initTime));
        assertArrayEquals(expected, actual);
    }
}
