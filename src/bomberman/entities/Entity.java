package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import javafx.scene.image.Image;

public abstract class Entity {
    protected int width = 27; // 32
    protected int height = 27; // 32
    protected int x;
    protected int y;
    protected boolean active = true;
    protected Image image;
    protected int layer = 0;

    public Entity(int xUnit, int yUnit, Image image, int layer) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.image = image;
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRowIndex() {
        return (int) (this.y + this.height / 2.0) / TileMap.CELL_SIZE;
    }

    public int getColIndex() {
        return (int) (this.x + this.width / 2.0) / TileMap.CELL_SIZE;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void render(RenderWindow renderWindow) {
        renderWindow.render(image, x, y);
    }

    public abstract void update();

    public void onCollision(Entity other) {
        //
    }


    public boolean intersects(Entity other) {
        boolean xCollision = (this.x < other.x + other.width) && (this.x + this.width > other.x);
        boolean yCollision = (this.y < other.y + other.height) && (this.y + this.height > other.y);
        return xCollision && yCollision;
    }
}
