import org.junit.Test;

import java.util.*;
import java.util.stream.LongStream;

import static junit.framework.TestCase.assertEquals;

public class BattleShips {

    public static Map<String, Double> damagedOrSunk(final int[][] board, final int[][] attacks) {
        Collection<Coordinate> coordinates = obtainCoordinates(board, attacks);
        Map<String, Double> results = new HashMap<>();
        results.put("sunk", obtainSunk(coordinates));
        results.put("damaged", obtainDamaged(coordinates));
        results.put("notTouched", obtainNotTouched(coordinates));
        results.put("points", obtainPoints(results));
        return results;
    }

    private static List<Coordinate> obtainCoordinates(int[][] board, int[][] attacks) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Coordinate coordinate = new BattleShips.Coordinate();
                coordinate.setX(j+1);
                coordinate.setY(board.length-i);
                coordinate.setBoat(board[i][j]);
                coordinates.add(coordinate);
            }
        }
        for(int i = 0; i<attacks.length;i++){
            final int x = attacks[i][0];
            final int y = attacks[i][1];
            Coordinate attacked = coordinates.stream().filter(elem->elem.getX()==x).filter(elem->elem.getY()==y).findFirst().get();
            attacked.setAtacked(true);
        }

        return coordinates;
    }

    private static Double obtainPoints(Map<String, Double> results) {
        Double points = 0d;
        points += results.get("sunk");
        points -= results.get("notTouched");
        points += results.get("damaged")*0.5;
        return points;
    }

    private static Double obtainNotTouched(Collection<Coordinate> coordinates) {
        Long notTouchedBoats = LongStream.rangeClosed(1,obtainNumberOfBoats(coordinates)).filter(elem->isNotTouched(elem,coordinates)).count();
        return new Double(notTouchedBoats);
    }

    private static Double obtainDamaged(Collection<Coordinate> coordinates) {
        Long damagedBoats = LongStream.rangeClosed(1,obtainNumberOfBoats(coordinates)).filter(m->!isSunk(m,coordinates)).filter(n->!isNotTouched(n,coordinates)).count();
        return new Double(damagedBoats);
    }

    private static Double obtainSunk(Collection<Coordinate> coordinates) {
        Long sunkBoats = LongStream.rangeClosed(1,obtainNumberOfBoats(coordinates)).filter(elem->isSunk(elem,coordinates)).count();
        return new Double(sunkBoats);
    }

    private static boolean isSunk(long elem, Collection<Coordinate> coordinates) {
        return coordinates.stream().filter(m->m.getBoat()==elem).allMatch(n -> n.isAtacked());
    }

    private static boolean isNotTouched(long elem, Collection<Coordinate> coordinates) {
        return coordinates.stream().filter(m->m.getBoat()==elem).allMatch(n -> !n.isAtacked());
    }


    private static long obtainNumberOfBoats(Collection<Coordinate> coordinates) {
        return  coordinates.stream().filter(elem->elem.getBoat()!=0).map(elem -> elem.getBoat()).distinct().count();
    }


    @Test
    public void example1() {
        int[][] board = new int[][]{new int[]{0, 0, 1, 0},
                new int[]{0, 0, 1, 0},
                new int[]{0, 0, 1, 0}};
        int[][] attacks = new int[][]{new int[]{3, 1}, new int[]{3, 2}, new int[]{3, 3}};

        Map<String, Double> expected = new HashMap<String, Double>();
        expected.put("sunk", 1.0);
        expected.put("damaged", .0);
        expected.put("notTouched", .0);
        expected.put("points", 1.0);

        assertEquals(expected, BattleShips.damagedOrSunk(board, attacks));
    }

    @Test
    public void example2() {
        int[][] board = new int[][]{new int[]{3, 0, 1},
                new int[]{3, 0, 1},
                new int[]{0, 2, 1},
                new int[]{0, 2, 0}};
        int[][] attacks = new int[][]{new int[]{2, 1}, new int[]{2, 2}, new int[]{3, 2}, new int[]{3, 3}};

        Map<String, Double> expected = new HashMap<String, Double>();
        expected.put("sunk", 1.0);
        expected.put("damaged", 1.0);
        expected.put("notTouched", 1.0);
        expected.put("points", 0.5);

        assertEquals(expected, BattleShips.damagedOrSunk(board, attacks));
    }

    public static class Coordinate {
        private int x;
        private int y;
        private int boat;
        private boolean atacked;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getBoat() {
            return boat;
        }

        public void setBoat(int boat) {
            this.boat = boat;
        }

        public boolean isAtacked() {
            return atacked;
        }

        public void setAtacked(boolean atacked) {
            this.atacked = atacked;
        }

    }
}