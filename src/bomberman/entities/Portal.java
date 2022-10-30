package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.map.TileMap;

public class Portal extends Entity {
    private final TileMap map;

    public Portal(int xUnit, int yUnit, int layer, TileMap map) {
        super(xUnit, yUnit, Sprite.portal.getFxImage(), layer);
        this.map = map;
    }

    @Override
    public void onCollision(Entity other) {
        if (this.map.getEntityAt(this.getColIndex(), this.getRowIndex()) == null) {
            if (other instanceof Bomber && this.map.getCurrentEnemy() == 0) {
                this.setActive(false);
                this.map.goToWinState();
            }
        }
    }

    @Override
    public void update() {

    }
}
