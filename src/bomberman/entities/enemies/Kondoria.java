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

public class Kondoria extends Enemy {
    // di cham, xuyen tuong, duoi theo bomber
    private int KONDORIA_SCORE = 500;

    private AStar aStar = new AStar();

    private Random random = new Random();

    public Kondoria(int xUnit, int yUnit, TileMap map) {
        super(xUnit, yUnit, map);
        this.speed = 1;
    }

    @Override
    public void render(RenderWindow renderWindow) {
        if (this.animationDir == Direction.LEFT) {
            renderWindow.render(Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        } else if (this.animationDir == Direction.RIGHT) {
            renderWindow.render(Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        }
    }

    @Override
    protected Vector2i getNextMove() {
        int playerRowPos = this.map.getBomber().getRowIndex();
        int playerColPos = this.map.getBomber().getColIndex();
        int[][] board = convertMap(this.map.getBoard());
        return aStar.nextMoveByAStar(board, new Pair<>(this.getRowIndex(),
                this.getColIndex()), new Pair<>(playerRowPos, playerColPos));
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
            this.map.addAnimation(Sprite.kondoria_dead, Sprite.kondoria_dead, Sprite.kondoria_dead, this.getColIndex(), this.getRowIndex());
            this.map.increaseScore(KONDORIA_SCORE);
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
                res[i][j] = 1;
            }
        }
        return res;
    }

}
