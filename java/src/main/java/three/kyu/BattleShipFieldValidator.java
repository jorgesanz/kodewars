package three.kyu;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BattleShipFieldValidator {

    public static int FIELD_SIZE= 10;
    public static int OCCUPIED_SPACE = 4*1 + 3*2 + 2*3 + 1*4;

    public static boolean fieldValidator(int[][] field) {
        try{
            validateOccupiedSpace(field);
            Map<Integer,Integer> boatCount = countBoats(field);
            validateBoatCount(boatCount);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private static void validateBoatCount( Map<Integer,Integer> boatCount) throws Exception {
        if (boatCount.get(1)!=4 || boatCount.get(2)!=3 || boatCount.get(3)!=2 || boatCount.get(4)!=1) throw new Exception();
    }

    private static Map<Integer,Integer> countBoats(int[][] field) throws Exception {
        Map<Integer,Integer> boatCount = new HashMap<>();
        for(int i = 0; i< FIELD_SIZE; i++){
            for(int j= 0; j<FIELD_SIZE; j++){
                if(field[i][j]==1){
                    int size = obtainSizeAndClean(i,j,field);
                    if(boatCount.containsKey(size)){
                        boatCount.put(size, boatCount.get(size)+1);
                    }
                    else{
                        boatCount.put(size,1);
                    }
                }
            }
        }
        return boatCount;
    }

    private static int obtainSizeAndClean(int i, int j, int[][] field) throws Exception {
        validateAround(i,j,field);
        if (isSubmarine(i,j,field)){
            field[i][j]=2;
            return 1;
        }
        else if(isSomethingRight(i,j,field)){
            int size = countRight(i,j,field);
            cleanRight(i,j,field,size);
            return size;
        }
        else if (isSomethingDown(i,j,field)){
            int size = countDown(i,j,field);
            cleanDown(i,j,field,size);
            return size;
        }
        else throw new Exception();
    }

    private static void cleanDown(int i, int j, int[][] field, int size) {
        for (int k=0;k<size;k++){
            field[i+k][j]=2;
        }
    }

    private static void cleanRight(int i, int j, int[][] field, int size) {
        for (int k=0;k<size;k++){
            field[i][j+k]=2;
        }
    }

    private static int countDown(int i, int j, int[][] field) throws Exception {
        if(isSomethingRight(i, j, field)||isSomethingRightDown(i, j, field))throw new Exception();
        if(!isSomethingDown(i,j,field)) return 1;
        else{
            return 1+countDown(i+1, j, field);
        }
    }

    private static int countRight(int i, int j, int[][] field) throws Exception {
        if(isSomethingDownLeft(i, j, field)||isSomethingDown(i, j, field)||isSomethingRightDown(i, j, field)) throw new Exception();
        if(!isSomethingRight(i,j,field)) return 1;
        else{
            return 1+countRight(i, j+1, field);
        }
    }

    private static void validateAround(int i, int j, int[][] field) throws Exception {
        int count = 0;
        if (isSomethingRight(i,j,field)) count ++;
        if (isSomethingRightDown(i,j,field)) count ++;
        if (isSomethingDown(i,j,field)) count ++;
        if (isSomethingDownLeft(i,j,field)) count ++;
        if(count > 1) throw new Exception();
    }

    private static boolean isSubmarine(int i, int j, int[][] field) {
        if(!isSomethingAround(i,j,field)){
            return true;
        }else{
            return false;
        }
    }

    private static boolean isSomethingAround(int i, int j, int[][] field) {
        return isSomethingRight(i,j,field) || isSomethingDown(i,j,field) || isSomethingRightDown(i,j,field) || isSomethingDownLeft(i,j,field);
    }

    private static boolean isSomethingDownLeft(int i, int j, int[][] field) {
        return ( i+1!=FIELD_SIZE && j!=0 && field[i+1][j-1]!=0);
    }

    private static boolean isSomethingRightDown(int i, int j, int[][] field) {
        return ( i+1!=FIELD_SIZE && j+1!=FIELD_SIZE && field[i+1][j+1]!=0);
    }

    private static boolean isSomethingDown(int i, int j, int[][] field) {
        return ( i+1!=FIELD_SIZE && field[i+1][j]!=0);
    }

    private static boolean isSomethingRight(int i, int j, int[][] field) {
        return ( j+1!=FIELD_SIZE && field[i][j+1]!=0);
    }

    private static void validateOccupiedSpace(int[][] field) throws Exception {
        int occupiedSpace = 0;
        for(int i =0; i< FIELD_SIZE; i++){
            for(int j=0; j< FIELD_SIZE; j++ ){
                occupiedSpace += field[i][j];
            }
        }
        if(occupiedSpace!=OCCUPIED_SPACE){
            throw new Exception();
        }
    }

    private static int[][] battleField = {
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    @Test
    public void SampleTest() {
        assertEquals(true, BattleShipFieldValidator.fieldValidator(battleField));
    }
}
