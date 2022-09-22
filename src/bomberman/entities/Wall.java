package bomberman.entities;

import bomberman.graphics.Sprite;

public class Wall extends Entity {
    public Wall(int xUnit, int yUnit, int layer) {
        super(xUnit, yUnit, Sprite.wall.getFxImage(), layer);
    }

    @Override
    public void update() {
        //
    }
}
