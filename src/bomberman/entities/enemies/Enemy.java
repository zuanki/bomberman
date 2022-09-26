package bomberman.entities.enemies;

import bomberman.entities.Direction;
import bomberman.entities.Entity;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import bomberman.utilities.Vector2i;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Entity {
    protected final TileMap map;

    protected Direction animationDir = Direction.RIGHT;
    protected int speed = 1;

    protected Vector2i movingVector = new Vector2i(1, 0);
    private Vector2i nextCell;

    public Enemy(int xUnit, int yUnit, TileMap map) {
        super(xUnit, yUnit, null, 2);
        nextCell = new Vector2i(xUnit, yUnit);
        this.map = map;
    }

    @Override
    public void render(RenderWindow renderWindow) {
        renderWindow.render(image, x, y);
    }

    @Override
    public void update() {
        // convert cell coordinate to world
        int nextX = nextCell.x * TileMap.CELL_SIZE;
        int nextY = nextCell.y * TileMap.CELL_SIZE;

        // check last move
        if (Math.abs(x - nextX) < speed && Math.abs(y - nextY) < speed) {
            x = nextX;
            y = nextY;
            nextCell = getNextMove();
            return;
        }

        if (x < nextX) {
            x += speed;
            animationDir = Direction.RIGHT;
            movingVector.x = 1;
            movingVector.y = 0;

        } else if (x > nextX) {
            x -= speed;
            animationDir = Direction.LEFT;
            movingVector.x = -1;
            movingVector.y = 0;
        } else if (y < nextY) {
            y += speed;
            movingVector.y = 1;
            movingVector.x = 0;
        } else if (y > nextY) {
            y -= speed;
            movingVector.y = -1;
            movingVector.x = 0;
        }
    }


    protected Vector2i getNextMove() {
        return nextCell;
    }

    protected boolean canMove(int xUnit, int yUnit) {
        return map.getEntityAt(xUnit, yUnit) == null;
    }

    protected List<Vector2i> getFreeCell() {
        List<Vector2i> res = new ArrayList<>();
        int r = this.getRowIndex();
        int c = this.getColIndex();
        if (this.canMove(c, r - 1)) {
            res.add(new Vector2i(c, r - 1));
        }
        if (this.canMove(c, r + 1)) {
            res.add(new Vector2i(c, r + 1));
        }
        if (this.canMove(c - 1, r)) {
            res.add(new Vector2i(c - 1, r));
        }
        if (this.canMove(c + 1, r)) {
            res.add(new Vector2i(c + 1, r));
        }
        return res;
    }
}
