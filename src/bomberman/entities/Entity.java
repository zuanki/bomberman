package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.render.RenderWindow;
import javafx.scene.image.Image;

public abstract class Entity {
    protected int width = 32;
    protected int height = 32;
    protected int x;
    protected int y;
    protected boolean active = true;
    protected Image image;

    public Entity(int xUnit, int yUnit, Image image) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.image = image;
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
