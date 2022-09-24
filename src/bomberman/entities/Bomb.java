package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;

public class Bomb extends Entity {
    private final TileMap map;
    private int timer = 2 * 60;
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
        renderWindow.render(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, (int) System.currentTimeMillis(),
                400).getFxImage(), this.x, this.y);
    }

    @Override
    public void update() {
        --timer;
        if (timer == 0) {
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
        int radius = this.map.levelFlame;
        this.map.addFlame(c, r, Flame.Type.CENTER);
        int[] dir_r = {0, 0, -1, 1};
        int[] dir_c = {-1, 1, 0, 0};
        for (int i = 0; i < 4; ++i) {
            for (int t = 1; t <= radius; ++t) {
                int x = r + t * dir_r[i];
                int y = c + t * dir_c[i];
                Entity tmp = this.map.getEntityAt(y, x);
                if (tmp instanceof Wall) {
                    break;
                } else if (tmp instanceof Brick) {
                    //tmp = (Brick) tmp;
                    ((Brick) tmp).eliminate();
                    this.map.addAnimation(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, y, x);
                    break;
                } else {
                    this.map.addFlame(y, x, this.getFlameType(y, x, radius));
                }
            }
        }
        /*boolean up = false, down = false, left = false, right = false;
        Entity[][] tmp = this.map.getBoard();
        for (int i = 0; i < level; ++i) {
            if (tmp[c][r - 1 - i] != null) {
                up = true;
                System.out.println("UP");
                break;
            }
            this.map.addFlame(c, r - 1 - i, Flame.Type.VERTICAL);
        }
        for (int i = 0; i < level; ++i) {
            if (tmp[c][r + 1 + i] != null) {
                down = true;
                System.out.println("DOWN");
                break;
            }
            this.map.addFlame(c, r + 1 + i, Flame.Type.VERTICAL);
        }
        for (int i = 0; i < level; ++i) {
            if (tmp[c - 1 - i][r] != null) {
                left = true;
                System.out.println("LEFT");
                break;
            }
            this.map.addFlame(c - 1 - i, r, Flame.Type.HORIZONTAL);
        }
        for (int i = 0; i < level; ++i) {
            if (tmp[c + 1 + i][r] != null) {
                right = true;
                System.out.println("RIGHT");
                break;
            }
            this.map.addFlame(c + 1 + i, r, Flame.Type.HORIZONTAL);
        }
        if (!up && (r - 1 - level) >= 0 && tmp[c][r - 1 - level] != null) {
            this.map.addFlame(c, r - 1 - level, Flame.Type.VERTICAL_TOP);
        }
        if (!down && (r + 1 + level) < tmp.length && tmp[c][r + 1 + level] != null) {
            this.map.addFlame(c, r + 1 + level, Flame.Type.VERTICAL_DOWN);
        }
        if (!left && (c - 1 - level) >= 0 && tmp[c - 1 - level][r] != null) {
            this.map.addFlame(c - 1 - level, r, Flame.Type.HORIZONTAL_LEFT);
        }
        if (!right && (c + 1 + level) < tmp[0].length && tmp[c + 1 + level][r] != null) {
            this.map.addFlame(c + 1 + level, r, Flame.Type.HORIZONTAL_RIGHT);
        }*/
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
