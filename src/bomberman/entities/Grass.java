package bomberman.entities;

import bomberman.graphics.Sprite;

public class Grass extends Entity {
    public Grass(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.grass.getFxImage());
    }

    @Override
    public void update() {
        //
    }
}
