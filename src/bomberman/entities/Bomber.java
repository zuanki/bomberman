package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.inputs.KeyPolling;
import bomberman.map.TileMap;
import bomberman.utilities.Vector2f;
import bomberman.utilities.Vector2i;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Bomber extends Entity {
    public static int BOMB_PLANT_DELAY = 60 / 2;
    public static int bombPlantTimer = 0;
    private final int playerWidth = 24; //24
    private final int playerHeight = 32; //32
    private final TileMap map;

    private int moveSpeed = 3;

    private boolean hasWon = false;


    public Bomber(int xUnit, int yUnit, Image image, TileMap map, int layer) {
        super(xUnit, yUnit, image, layer);
        super.setSize(this.width, this.height);
        this.map = map;
    }


    @Override
    public void update() {

        --bombPlantTimer;
        long time = System.currentTimeMillis();

        if (KeyPolling.getInstance().isKeyDown(KeyCode.W)) {
            this.y -= this.moveSpeed;
            this.image = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, time, 300).getFxImage();

        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.S)) {
            this.y += this.moveSpeed;
            this.image = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, time, 300).getFxImage();
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.A)) {
            this.x -= this.moveSpeed;
            this.image = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, time, 300).getFxImage();
        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.D)) {
            this.x += this.moveSpeed;
            this.image = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, time, 300).getFxImage();
        }
        //
        //
        if (KeyPolling.getInstance().isKeyDown(KeyCode.K)) {
            //System.out.println("pressed space");
            if (this.map.getCurrentBomb() > 0 && bombPlantTimer < 0) {
                bombPlantTimer = BOMB_PLANT_DELAY;
                this.map.descreaseBomb();
                this.map.addBomb(this.getColIndex(), this.getRowIndex());
            }
        }
    }

    public int getPlayerWidth() {
        return playerWidth;
    }

    public int getPlayerHeight() {
        return playerHeight;
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
        // After
        this.x += resolveX;
        this.y += resolveY;
        //System.out.println(this.x + " " + this.y);
    }

    @Override
    public void onCollision(Entity other) {
        if (other instanceof Wall) {
            this.afterCollision(other);
        }
        if (other instanceof Brick) {
            this.afterCollision(other);
        }
        if (other instanceof Bomb bomb) {
            if (bomb.isPlayerLeft()) {
                this.afterCollision(other);
            }
        }
        if (other instanceof Flame) {
            this.die();
            //System.out.println("Player dead animation");
            //System.out.println(this.getColIndex() + " "  + this.getRowIndex());
            //System.out.println(this.x + " "+  this.y);
        }
    }

    public void die() {
        //System.out.println("Unactive!!!");
        this.setActive(false);
        this.map.gotoNextState();
        this.map.addAnimation(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, this.getColIndex(), this.getRowIndex());
    }

    public void increaseSpeed() {
        this.moveSpeed += 2;
    }

    public Vector2i getPosition() {
        return new Vector2i(this.getColIndex(), this.getRowIndex());
    }
}
