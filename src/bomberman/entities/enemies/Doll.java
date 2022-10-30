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

import java.util.Random;

public class Doll extends Enemy {
    // luc nhanh luc cham, thinh thoang doi huong, thinh thong duoi theo bomber
    private final AStar aStar = new AStar();
    private int DOLL_SCORE = 300;
    private Random random = new Random();

    public Doll(int xUnit, int yUnit, TileMap map) {
        super(xUnit, yUnit, map);
    }

    @Override
    public void render(RenderWindow renderWindow) {
        if (this.animationDir == Direction.LEFT) {
            renderWindow.render(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        } else if (this.animationDir == Direction.RIGHT) {
            renderWindow.render(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        }
    }

    @Override
    protected Vector2i getNextMove() {
        /*List<Vector2i> freeCells = getFreeCell();
        if (freeCells.isEmpty()) {
            return new Vector2i(getRowIndex(), getColIndex());
        }
        int index = (int) (Math.random() * freeCells.size());
        return freeCells.get(index);*/
        int r = this.getRowIndex();
        int c = this.getColIndex();
        if (this.random.nextBoolean()) {
            this.randomSpeed();

            this.movingVector = this.randomDirection();
            // Try to move current dir
            if (canMove(c + movingVector.x, r + movingVector.y)) {
                return new Vector2i(c + movingVector.x, r + movingVector.y);
                //return new Vector2i(0, 0);
            } else if (canMove(c - movingVector.x, r - movingVector.y)) {
                return new Vector2i(c - movingVector.x, r - movingVector.y);
                //return new Vector2i(0, 0);
            }
        } else {
            int playerRowPos = this.map.getBomber().getRowIndex();
            int playerColPos = this.map.getBomber().getColIndex();
            int[][] board = convertMap(this.map.getBoard());
            return aStar.nextMoveByAStar(board, new Pair<>(this.getRowIndex(),
                    this.getColIndex()), new Pair<>(playerRowPos, playerColPos));
        }

        return new Vector2i(c, r);
        //return new Vector2i(0, 0);
    }

    @Override
    public void onCollision(Entity other) {
        if (other instanceof Flame) {
            this.setActive(false);
            if (this.map.getCurrentEnemy() > 0 && enemyTimeDelay < 0) {
                enemyTimeDelay = ENEMY_TIME_DELAY;
                this.map.descreaseEnemy();
            }
            this.map.addAnimation(Sprite.doll_dead, Sprite.doll_dead, Sprite.doll_dead, this.getColIndex(), this.getRowIndex());
            this.map.increaseScore(DOLL_SCORE);
        }
        if (other instanceof Bomber bomber) {
            bomber.die();
        }
    }

    private void randomSpeed() {

        if (random.nextBoolean()) {
            this.speed = 5;
        } else {
            this.speed = 1;
        }
    }

    private Vector2i randomDirection() {
        if (this.random.nextBoolean()) {
            return new Vector2i(1, 0);
        } else {
            return new Vector2i(0, 1);
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
}
