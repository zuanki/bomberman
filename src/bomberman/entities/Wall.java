package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Wall extends Entity{
    public Wall(int xUnit, int yUnit){
        super(xUnit, yUnit, Sprite.wall.getFxImage());
    }
    @Override
    public void update() {
        //
    }
}
