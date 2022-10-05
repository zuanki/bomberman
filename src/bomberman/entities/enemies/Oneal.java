package bomberman.entities.enemies;

import bomberman.algorithm.AStar;
import bomberman.entities.Bomber;
import bomberman.entities.Direction;
import bomberman.entities.Entity;
import bomberman.entities.Flame;
import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import bomberman.utilities.Vector2i;
import javafx.util.Pair;

import java.util.List;


public class Oneal extends Enemy {
    //private List<Pair<Integer, Integer>> listMoveAStar = new ArrayList<>();
    private final AStar aStar = new AStar();
    private final int radius = 3;

    public Oneal(int xUnit, int yUnit, TileMap map) {
        super(xUnit, yUnit, map);
    }

    @Override
    public void render(RenderWindow renderWindow) {
        if (this.animationDir == Direction.LEFT) {
            renderWindow.render(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        } else if (this.animationDir == Direction.RIGHT) {
            renderWindow.render(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        }
    }

    @Override
    protected Vector2i getNextMove() {
        // Nguyen Hoa
        if (this.canDetectBomber()) {
            int playerRowPos = this.map.getBomber().getRowIndex();
            int playerColPos = this.map.getBomber().getColIndex();
            int[][] board = convertMap(this.map.getBoard());
            return aStar.nextMoveByAStar(board, new Pair<>(this.getRowIndex(),
                    this.getColIndex()), new Pair<>(playerRowPos, playerColPos));
        } else {
            List<Vector2i> freeCells = getFreeCell();
            if (freeCells.isEmpty()) {
                return new Vector2i(getRowIndex(), getColIndex());
            }
            int index = (int) (Math.random() * freeCells.size());
            return freeCells.get(index);
        }
        // Pair (row, col)
        /*System.out.println("Enemy :");
        System.out.println(this.getColIndex() + " " + this.getRowIndex());
        System.out.println("Bomber :");
        System.out.println(playerXPos + " " + playerYPos);*/
        //return aStar.nextMoveByAStar(board, new Pair<>(this.getColIndex(), this.getRowIndex()), new Pair<>(12, 15));
        //return new Vector2i(this.getColIndex(), this.getRowIndex());

        // Move random

        /*List<Vector2i> freeCells = getFreeCell();
        if (freeCells.isEmpty()) {
            return new Vector2i(getRowIndex(), getColIndex());
        }
        int index = (int) (Math.random() * freeCells.size());*/


        // Check convert map


       /* int[][] tmp = this.convertMap(this.map.getBoard());
        for (int[] ints : tmp) {
            for (int j = 0; j < tmp[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println("\n");
        }
        System.out.println("Hello");*/
        //return freeCells.get(index);

        // Nguyen Hoa

        /*int playerXPos = this.map.getBomber().getRowIndex();
        int playerYPos = this.map.getBomber().getColIndex();
        Pair<Integer, Integer> src = new Pair<>(this.getRowIndex(), this.getColIndex());
        Pair<Integer, Integer> dest = new Pair<>(playerYPos, playerXPos);
        // convert Entity to int[][]
        System.out.println("Pos of enemy (src):");
        System.out.println(src.getKey() + " " + src.getValue());
        System.out.println("Pos of player (dest):");
        System.out.println(dest.getKey() + " " + dest.getValue());
        main.aStarSearch(convertMap(this.map.getBoard(), ROW, COL), src, new Pair<>(ROW - 2, COL - 2));
        this.listMoveAStar = main.listMove;
        System.out.println(listMoveAStar.get(0).getValue() + " " + listMoveAStar.get(0).getKey());
        if (listMoveAStar.size() == 0) {
            return new Vector2i(0, 0);
        } else {
            return new Vector2i(listMoveAStar.get(1).getKey(), listMoveAStar.get(1).getValue());
        }*/
    }

    @Override
    public void onCollision(Entity other) {
        if (other instanceof Flame) {
            this.setActive(false);
            this.map.addAnimation(Sprite.oneal_dead, Sprite.oneal_dead, Sprite.oneal_dead, this.getColIndex(), this.getRowIndex());
        }
        if (other instanceof Bomber bomber) {
            bomber.die();
        }
    }

    private int[][] convertMap(Entity[][] entities) {
        int row = entities.length;
        int col = entities[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (entities[i][j] == null) {
                    res[i][j] = 1;
                } else {
                    res[i][j] = 0;
                }
            }
        }
        return res;
    }

    private boolean canDetectBomber() {
        int srcXPos = this.getColIndex();
        int srcYPos = this.getRowIndex();
        int destXPos = this.map.getBomber().getColIndex();
        int destYPos = this.map.getBomber().getRowIndex();
        return Math.abs(srcXPos - destXPos) <= this.radius && Math.abs(srcYPos - destYPos) <= this.radius;
    }
}

