package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;

public class Bomb extends Entity {
    private int timer = 2 * 60;
    private TileMap map;
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
        this.map.addFlame(c, r, Flame.Type.CENTER);
        this.map.addFlame(c, r - 1, Flame.Type.VERTICAL_TOP);
        this.map.addFlame(c, r + 1, Flame.Type.VERTICAL_DOWN);
        this.map.addFlame(c + 1, r, Flame.Type.HORIZONTAL_RIGHT);
        this.map.addFlame(c - 1, r, Flame.Type.HORIZONTAL_LEFT);
    }
}
