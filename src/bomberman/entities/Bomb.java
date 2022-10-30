package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import bomberman.sound.Sound;

public class Bomb extends Entity {
    private final TileMap map;
    private int timer = 5 * 30;
    private boolean playerLeft = false;

    public Bomb(int xUnit, int yUnit, TileMap map, int layer) {
        super(xUnit, yUnit, Sprite.bomb.getFxImage(), layer);
        this.map = map;
    }



    public boolean isPlayerLeft() {
        return playerLeft;
    }

    public void setPlayerLeft(boolean playerLeft) {
        this.playerLeft = playerLeft;
    }


    @Override
    public void render(RenderWindow renderWindow) {
        renderWindow.render(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, (long) System.currentTimeMillis(),
                400).getFxImage(), this.x, this.y);
    }

    @Override
    public void update() {

        --timer;
        if (timer == 0) {
          //  Bomber.bombOK = true;
            this.die();
        }
        if (!this.intersects(this.map.getBomber())) {
            this.playerLeft = true;
        }

    }

    private void die() {
        this.active = false;
        int r = this.getRowIndex();
        int c = this.getColIndex();
        int radius = TileMap.FLAME;
        this.map.addFlame(c, r, Flame.Type.CENTER);
        int[] dir_r = {0, 0, -1, 1};
        int[] dir_c = {-1, 1, 0, 0};
        for (int i = 0; i < 4; ++i) {
            for (int t = 1; t <= radius; ++t) {
                int x = r + t * dir_r[i];
                int y = c + t * dir_c[i];
                Entity tmp = this.map.getEntityAt(y,x);
                if (tmp instanceof Wall) {
                    Sound.play_A("bomb");
                    break;
                } else if (tmp instanceof Brick) {
                    //tmp = (Brick) tmp;
                    ((Brick) tmp).eliminate();
                    Sound.play_A("bomb");
                  //  controlGame.setCollision(true);
                    this.map.addAnimation(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, y,x);
                    break;
                } else {
                    Sound.play_A("bomb");
                    this.map.addFlame(y, x, this.getFlameType(y, x, radius));
                }
            }
        }

    }

    public Flame.Type getFlameType(int xUnit, int yUnit, int radius) {
        int dir_r = yUnit - this.getRowIndex();
        int dir_c = xUnit - this.getColIndex();
        if (dir_r == radius) {
            return Flame.Type.VERTICAL_DOWN;
        }
        if (dir_r == -radius) {
            return Flame.Type.VERTICAL_TOP;
        }
        if (dir_c == radius) {
            return Flame.Type.HORIZONTAL_RIGHT;
        }
        if (dir_c == -radius) {
            return Flame.Type.HORIZONTAL_LEFT;
        }
        if (dir_r != 0) {
            return Flame.Type.VERTICAL;
        }
        if (dir_c != 0) {
            return Flame.Type.HORIZONTAL;
        }
        return null;
    }
}
