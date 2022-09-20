package bomberman.entities;

import bomberman.inputs.KeyPolling;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Bomber extends Entity {

    public Bomber(int xUnit, int yUnit, Image image) {
        super(xUnit, yUnit, image);
    }

    @Override
    public void update() {

        if (KeyPolling.getInstance().isKeyDown(KeyCode.W)) {
            y -= 1;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.S)) {
            y += 1;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.A)) {
            x -= 1;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.D)) {
            x += 1;
        }
    }
}
