package bomberman.entities;

import bomberman.graphics.Sprite;

public class Brick extends Entity {
    public Brick(int xUnit, int yUnit, int layer) {
        super(xUnit, yUnit, Sprite.brick.getFxImage(), layer);
    }

    public void eliminate() {
        this.setActive(false);
    }

    @Override
    public void update() {

    }
}
