package bomberman.entities;

import bomberman.inputs.KeyPolling;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Bomber extends Entity {
    private final int playerWidth = 24;
    private final int playerHeight = 32;
    boolean willDie = false;
    private int timer = 120; //120 / 60 = 2

    public Bomber(int xUnit, int yUnit, Image image) {
        super(xUnit, yUnit, image);
    }

    @Override
    public void update() {
        if (willDie) {
            --timer;
            if (timer == 0) {
                active = false;
            }
        }

        if (KeyPolling.getInstance().isKeyDown(KeyCode.W)) {
            y -= 3;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.S)) {
            y += 3;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.A)) {
            x -= 3;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.D)) {
            x += 3;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.X)) {
            willDie = true;
        }
    }

    @Override
    public void onCollision(Entity other) {
        //
        if (other instanceof Wall) {
            System.out.print("Collision with wall");
        }
    }
}
