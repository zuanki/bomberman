package bomberman.entities;

import bomberman.BombermanGame;
import bomberman.graphics.Sprite;
import bomberman.inputs.KeyPolling;
import bomberman.map.TileMap;
import bomberman.sound.Sound;
import bomberman.state.StatesManager;
import bomberman.utilities.Vector2f;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Bomber extends Entity {
    private final int playerWidth = 1; //24
    private final int playerHeight = 2; //32
    private final TileMap map;
    private final StatesManager statesManager = new StatesManager();
    //  private int moveSpeed = TileMap.SPEED;
    boolean willDie = false;
    private int timeDie = 0;
    private int timer = 120; //120 / 60 = 2
    private Sound sound = new Sound();

    public static boolean bombOK = false;
    static int bombCount = 0;
    BombermanGame bombermanGame = new BombermanGame();
    private int countDie = 0;

    public Bomber(int xUnit, int yUnit, Image image, TileMap map, int layer) {
        super(xUnit, yUnit, image, layer);
        super.setSize(this.width, this.height);
        this.map = map;

    }

    @Override
    public void update() {
        if (willDie) {
            --timer;

            if (timer == 0) {
               // this.active = false;

            }
        }

        if (KeyPolling.getInstance().isKeyDown(KeyCode.W)) {
            Sound.play_A("moving");

            this.y -= TileMap.SPEED / 32;
            this.image = Sprite.movingSprite(Sprite.player_up_2, Sprite.player_up_1, Sprite.player_up, System.currentTimeMillis(), 250).getFxImage();


        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.S)) {
            Sound.play_A("moving");
            this.y += TileMap.SPEED / 32;
            this.image = Sprite.movingSprite(Sprite.player_down_2, Sprite.player_down_1, Sprite.player_down, System.currentTimeMillis(), 300).getFxImage();

        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.A)) {
            Sound.play_A("moving");
            this.x -= TileMap.SPEED / 32;
            this.image = Sprite.movingSprite(Sprite.player_left_2, Sprite.player_left_1, Sprite.player_left, System.currentTimeMillis(), 300).getFxImage();

        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.D)) {

            Sound.play_A("moving");
            this.x += TileMap.SPEED / 32;
            this.image = Sprite.movingSprite(Sprite.player_right_2, Sprite.player_right_1, Sprite.player_right, System.currentTimeMillis(), 400).getFxImage();

        } else if (KeyPolling.getInstance().isKeyDown(KeyCode.K)) {

            Sound.play_A("bomb-planted");
            this.map.addBomb(this.getColIndex(), this.getRowIndex());

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
        // After
        this.x += resolveX;
        this.y += resolveY;
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
        }
    }

    public void run_() {
        countDie++;
    }

    public void die() {
        willDie = true;
        Sound.play_A("death");
        this.setActive(false);
        this.map.addAnimation(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, this.getColIndex(), this.getRowIndex());
        TileMap.ISDIE = true;

    }
}

