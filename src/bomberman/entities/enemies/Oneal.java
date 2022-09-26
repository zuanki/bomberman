package bomberman.entities.enemies;

import bomberman.entities.Direction;
import bomberman.entities.Entity;
import bomberman.entities.Flame;
import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import bomberman.utilities.Vector2i;

import java.util.List;

public class Oneal extends Enemy {

    private boolean isLeft = true;

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
        List<Vector2i> freeCells = getFreeCell();
        if (freeCells.isEmpty()) {
            return new Vector2i(getRowIndex(), getColIndex());
        }
        int index = (int) (Math.random() * freeCells.size());
        return freeCells.get(index);
    }

    @Override
    public void onCollision(Entity other) {
        if (other instanceof Flame) {
            this.setActive(false);
        }
    }
}
