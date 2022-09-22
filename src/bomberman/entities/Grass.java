package bomberman.entities;

import bomberman.graphics.Sprite;

public class Grass extends Entity {
    public Grass(int xUnit, int yUnit, int layer) {
        super(xUnit, yUnit, Sprite.grass.getFxImage(), layer);
    }

    @Override
    public void update() {
        //
    }
}
