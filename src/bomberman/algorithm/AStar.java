package bomberman.algorithm;

import bomberman.utilities.Vector2i;
import javafx.util.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;


public class AStar {
    //public List<Pair<Integer, Integer>> listMove = new ArrayList<>();
    /*private int ROW = 9;
    private int COL = 10;
    private int[][] board;*/

    /*public AStar(int row, int col) {
        this.ROW = row;
        this.COL = col;
        this.board = new int[ROW][COL];
    }*/

    /*public static void main(String[] args) {
        AStar main = new AStar();
        int[][] board = new int[][]{
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1, 0, 0, 1}};
        //main.aStarSearch(board, new Pair<>(8, 0), new Pair<>(1, 6));
        Vector2i k = main.nextMoveByAStar(board, new Pair<>(8, 0), new Pair<>(7, 0));
        System.out.println(k.x + " " + k.y);
    }*/

    public Vector2i nextMoveByAStar(int[][] board, Pair<Integer, Integer> src
            , Pair<Integer, Integer> dest) {
        List<Pair<Integer, Integer>> res = new ArrayList<>();
        this.aStarSearch(board, src, dest, board.length, board[0].length, res);
        if (res.size() == 0) {
            return new Vector2i(src.getValue(), src.getKey());
        } else {
            return new Vector2i(res.get(1).getValue(), res.get(1).getKey());
        }
    }

    public List<Pair<Integer, Integer>> listMoveByAStar(int[][] board, Pair<Integer, Integer> src, Pair<Integer, Integer> dest) {
        List<Pair<Integer, Integer>> res = new ArrayList<>();
        this.aStarSearch(board, src, dest, board.length, board[0].length, res);
        return res;
    }

    private boolean isValid(int row, int col, int ROW, int COL) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    private boolean isUnBlocked(int[][] board, int row, int col) {
        return board[row][col] == 1;
    }

    private boolean isDestination(int row, int col, Pair<Integer, Integer> dest) {
        return (row == dest.getKey()) && (col == dest.getValue());
    }

    private double calculateHValue(int row, int col, Pair<Integer, Integer> dest) {
        return ((double) Math.sqrt((row - dest.getKey()) * (row - dest.getKey())
                + (col - dest.getValue()) * (col - dest.getValue())));
    }

    // parent_i
    // parent_j
    // f
    // g
    // h
    // f = g + h
    private void tracePath(List<List<Double[]>> cellDetails, Pair<Integer, Integer> dest, List<Pair<Integer, Integer>> listMove) {
        int row = dest.getKey();
        int col = dest.getValue();
        Stack<Pair<Integer, Integer>> Path = new Stack<>();
        while (!(cellDetails.get(row).get(col)[0] == row && cellDetails.get(row).get(col)[1] == col)) {
            Path.push(new Pair<>(row, col));
            int temp_row = (int) Math.floor(cellDetails.get(row).get(col)[0]);
            int temp_col = (int) Math.floor(cellDetails.get(row).get(col)[1]);
            row = temp_row;
            col = temp_col;
        }
        Path.push(new Pair<>(row, col));
        while (!Path.empty()) {
            Pair<Integer, Integer> p = Path.pop();
            listMove.add(new Pair<>(p.getKey(), p.getValue()));
        }
        return;
    }

    // A star search
    public void aStarSearch(int[][] board, Pair<Integer, Integer> src, Pair<Integer, Integer> dest,
                            int ROW, int COL, List<Pair<Integer, Integer>> listMove) {
        if (!isValid(src.getKey(), src.getValue(), ROW, COL)) {
            System.out.println("Src is invalid");
            return;
        }
        if (!isValid(dest.getKey(), dest.getValue(), ROW, COL)) {
            System.out.println("Dest is invalid");
            return;
        }
        if (!isUnBlocked(board, src.getKey(), src.getValue()) || !isUnBlocked(board, dest.getKey(), dest.getValue())) {
            System.out.println("Src or dest is blocked !!");
            return;
        }
        if (isDestination(src.getKey(), src.getValue(), dest)) {
            System.out.println("We are already at the dest");
            return;
        }
        boolean[][] closedList = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                closedList[i][j] = false;
            }
        }
        List<List<Double[]>> cellDetails = new ArrayList<>();
        for (int i = 0; i < ROW; i++) {
            List<Double[]> tmp = new ArrayList<>();
            for (int j = 0; j < COL; j++) {
                Double[] t = new Double[5];
                t[0] = -1.0;
                t[1] = -1.0;
                t[2] = Double.MAX_VALUE;
                t[3] = Double.MAX_VALUE;
                t[4] = Double.MAX_VALUE;
                tmp.add(t);
            }
            cellDetails.add(tmp);
        }
        // parent_i
        // parent_j
        // f
        // g
        // h
        // f = g + h
        int i, j;
        i = src.getKey();
        j = src.getValue();

        cellDetails.get(i).get(j)[2] = 0.0;
        cellDetails.get(i).get(j)[3] = 0.0;
        cellDetails.get(i).get(j)[4] = 0.0;
        cellDetails.get(i).get(j)[0] = (double) i;
        cellDetails.get(i).get(j)[1] = (double) j;

        TreeSet<Triplet<Double, Integer, Integer>> openList = new TreeSet<>();
        openList.add(new Triplet<>(0.0, i, j));
        boolean foundDest = false;
        while (!openList.isEmpty()) {
            Triplet<Double, Integer, Integer> p = openList.first();
            i = p.getValue1();
            j = p.getValue2();
            closedList[i][j] = true;
            openList.remove(openList.first());
            double gNew, hNew, fNew;
            // North
            if (isValid(i - 1, j, ROW, COL)) {
                if (isDestination(i - 1, j, dest)) {
                    cellDetails.get(i - 1).get(j)[0] = (double) i;
                    cellDetails.get(i - 1).get(j)[1] = (double) j;
                    tracePath(cellDetails, dest, listMove);
                    foundDest = true;
                    return;
                } else if (!closedList[i - 1][j] && isUnBlocked(board, i - 1, j)) {
                    gNew = cellDetails.get(i).get(j)[3] + 1.0;
                    hNew = calculateHValue(i - 1, j, dest);
                    fNew = gNew + hNew;

                    // parent_i
                    // parent_j
                    // f
                    // g
                    // h
                    // f = g + h
                    if (cellDetails.get(i - 1).get(j)[2] == Double.MAX_VALUE
                            || cellDetails.get(i - 1).get(j)[2] > fNew) {
                        openList.add(new Triplet<>(fNew, i - 1, j));
                        cellDetails.get(i - 1).get(j)[2] = fNew;
                        cellDetails.get(i - 1).get(j)[3] = gNew;
                        cellDetails.get(i - 1).get(j)[4] = hNew;
                        cellDetails.get(i - 1).get(j)[0] = (double) i;
                        cellDetails.get(i - 1).get(j)[1] = (double) j;
                    }
                }
            }
            // South
            if (isValid(i + 1, j, ROW, COL)) {
                if (isDestination(i + 1, j, dest)) {
                    cellDetails.get(i + 1).get(j)[0] = (double) i;
                    cellDetails.get(i + 1).get(j)[1] = (double) j;
                    tracePath(cellDetails, dest, listMove);
                    foundDest = true;
                    return;
                } else if (!closedList[i + 1][j] && isUnBlocked(board, i + 1, j)) {
                    gNew = cellDetails.get(i).get(j)[3] + 1.0;
                    hNew = calculateHValue(i + 1, j, dest);
                    fNew = gNew + hNew;

                    // parent_i
                    // parent_j
                    // f
                    // g
                    // h
                    // f = g + h
                    if (cellDetails.get(i + 1).get(j)[2] == Double.MAX_VALUE
                            || cellDetails.get(i + 1).get(j)[2] > fNew) {
                        openList.add(new Triplet<>(fNew, i + 1, j));
                        cellDetails.get(i + 1).get(j)[2] = fNew;
                        cellDetails.get(i + 1).get(j)[3] = gNew;
                        cellDetails.get(i + 1).get(j)[4] = hNew;
                        cellDetails.get(i + 1).get(j)[0] = (double) i;
                        cellDetails.get(i + 1).get(j)[1] = (double) j;
                    }
                }
            }
            // East
            if (isValid(i, j + 1, ROW, COL)) {
                if (isDestination(i, j + 1, dest)) {
                    cellDetails.get(i).get(j + 1)[0] = (double) i;
                    cellDetails.get(i).get(j + 1)[1] = (double) j;
                    tracePath(cellDetails, dest, listMove);
                    foundDest = true;
                    return;
                } else if (!closedList[i][j + 1] && isUnBlocked(board, i, j + 1)) {
                    gNew = cellDetails.get(i).get(j)[3] + 1.0;
                    hNew = calculateHValue(i, j + 1, dest);
                    fNew = gNew + hNew;

                    // parent_i
                    // parent_j
                    // f
                    // g
                    // h
                    // f = g + h
                    if (cellDetails.get(i).get(j + 1)[2] == Double.MAX_VALUE
                            || cellDetails.get(i).get(j + 1)[2] > fNew) {
                        openList.add(new Triplet<>(fNew, i, j + 1));
                        cellDetails.get(i).get(j + 1)[2] = fNew;
                        cellDetails.get(i).get(j + 1)[3] = gNew;
                        cellDetails.get(i).get(j + 1)[4] = hNew;
                        cellDetails.get(i).get(j + 1)[0] = (double) i;
                        cellDetails.get(i).get(j + 1)[1] = (double) j;
                    }
                }
            }
            // West
            if (isValid(i, j - 1, ROW, COL)) {
                if (isDestination(i, j - 1, dest)) {
                    cellDetails.get(i).get(j - 1)[0] = (double) i;
                    cellDetails.get(i).get(j - 1)[1] = (double) j;
                    tracePath(cellDetails, dest, listMove);
                    foundDest = true;
                    return;
                } else if (!closedList[i][j - 1] && isUnBlocked(board, i, j - 1)) {
                    gNew = cellDetails.get(i).get(j)[3] + 1.0;
                    hNew = calculateHValue(i, j - 1, dest);
                    fNew = gNew + hNew;

                    // parent_i
                    // parent_j
                    // f
                    // g
                    // h
                    // f = g + h
                    if (cellDetails.get(i).get(j - 1)[2] == Double.MAX_VALUE
                            || cellDetails.get(i).get(j - 1)[2] > fNew) {
                        openList.add(new Triplet<>(fNew, i, j - 1));
                        cellDetails.get(i).get(j - 1)[2] = fNew;
                        cellDetails.get(i).get(j - 1)[3] = gNew;
                        cellDetails.get(i).get(j - 1)[4] = hNew;
                        cellDetails.get(i).get(j - 1)[0] = (double) i;
                        cellDetails.get(i).get(j - 1)[1] = (double) j;
                    }
                }
            }
        }
        return;
    }
}
