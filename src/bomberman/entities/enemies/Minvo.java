package bomberman.entities.enemies;

import bomberman.entities.Bomber;
import bomberman.entities.Direction;
import bomberman.entities.Entity;
import bomberman.entities.Flame;
import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import bomberman.utilities.Vector2i;

import java.util.Random;

public class Minvo extends Enemy {
    // duoi theo bomber, biet tranh bomb
    private int MINVO_SCORE = 300;

    private int bomb_radius;

    private Random random = new Random();

    public Minvo(int xUnit, int yUnit, TileMap map) {
        super(xUnit, yUnit, map);
        this.bomb_radius = super.map.levelFlame;
    }

    @Override
    public void render(RenderWindow renderWindow) {
        if (this.animationDir == Direction.LEFT) {
            renderWindow.render(Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        } else if (this.animationDir == Direction.RIGHT) {
            renderWindow.render(Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3,
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
        this.randomSpeed();
        // Try to move current dir
        if (canMove(c + movingVector.x, r + movingVector.y)) {
            return new Vector2i(c + movingVector.x, r + movingVector.y);
            //return new Vector2i(0, 0);
        } else if (canMove(c - movingVector.x, r - movingVector.y)) {
            return new Vector2i(c - movingVector.x, r - movingVector.y);
            //return new Vector2i(0, 0);
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
            this.map.addAnimation(Sprite.minvo_dead, Sprite.minvo_dead, Sprite.minvo_dead, this.getColIndex(), this.getRowIndex());
            this.map.increaseScore(MINVO_SCORE);
        }
        if (other instanceof Bomber bomber) {
            bomber.die();
        }
    }

    public void randomSpeed() {
        if (random.nextBoolean()) {
            this.speed = 5;
        } else {
            this.speed = 1;
        }
    }
}
