package bomberman.entities;

import bomberman.inputs.KeyPolling;
import bomberman.utilities.Vector2f;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Bomber extends Entity {
    private final int playerWidth = 24;
    private final int playerHeight = 32;
    boolean willDie = false;
    private int moveSpeed = 3;
    private int timer = 120; //120 / 60 = 2

    public Bomber(int xUnit, int yUnit, Image image) {
        super(xUnit, yUnit, image);
        super.setSize(this.width, this.height);
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
            this.y -= this.moveSpeed;

        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.S)) {
            this.y += this.moveSpeed;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.A)) {
            this.x -= this.moveSpeed;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.D)) {
            this.x += this.moveSpeed;
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.X)) {
            willDie = true;
        }
    }

    private void afterCollision(Entity other) {
        Vector2f playerHalfSize = new Vector2f((float) this.width / 2, (float) this.height / 2);
        Vector2f playerCenter = new Vector2f(this.x + playerHalfSize.x, this.y + playerHalfSize.y);
        Vector2f otherHalfSize = new Vector2f((float) other.width / 2, (float) other.height / 2);
        Vector2f otherCenter = new Vector2f(other.x + otherHalfSize.x, other.y + otherHalfSize.y);
        float disX = playerCenter.x - otherCenter.x;
        float disY = playerCenter.y - otherCenter.y;
        float sumHalfSizeX = playerHalfSize.x + otherHalfSize.x;
        float sumHalfSizeY = playerHalfSize.y + otherHalfSize.y;
        float overlapX = sumHalfSizeX - Math.abs(disX);
        float overlapY = sumHalfSizeY - Math.abs(disY);
        float resolveX = 0;
        float resolveY = 0;
        if (overlapX < overlapY) {
            resolveX = (disX > 0) ? overlapX : -overlapX;
        } else {
            resolveY = (disY > 0) ? overlapY : -overlapY;
        }
        this.x += resolveX;
        this.y += resolveY;
        System.out.println(this.x + " " + this.y);
    }

    @Override
    public void onCollision(Entity other) {
        //
        if (other instanceof Wall) {
            this.afterCollision(other);
        }
    }
}
