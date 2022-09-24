package bomberman.entities.enemies;

import bomberman.entities.Entity;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import bomberman.utilities.Vector2i;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemies extends Entity {
    protected final TileMap map;

    public Enemies(int xUnit, int yUnit, TileMap map) {
        super(xUnit, yUnit, null, 2);
        this.map = map;
    }

    @Override
    public void render(RenderWindow renderWindow) {
        renderWindow.render(image, x, y);
    }

    @Override
    public void update() {
        //
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
        } else if (this.canMove(c, r + 1)) {
            res.add(new Vector2i(c, r + 1));
        } else if (this.canMove(c - 1, r)) {
            res.add(new Vector2i(c - 1, r));
        } else if (this.canMove(c + 1, r)) {
            res.add(new Vector2i(c + 1, r));
        }
        return res;
    }
}
