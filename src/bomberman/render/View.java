package bomberman.render;

import bomberman.utilities.Vector2i;

public class View {
    private Vector2i position;
    private Vector2i size;

    public View() {
    }

    public View(Vector2i position, Vector2i size) {
        this.position = position;
        this.size = size;
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.setSize(size.x, size.y);
    }

    public Vector2i getPosition() {
        return position;
    }

    public void setPosition(Vector2i position) {
        this.setPosition(position.x, position.y);
    }

    public void move(int offsetX, int offsetY) {
        this.position.x += offsetX;
        this.position.y += offsetY;
    }

    public void move(Vector2i offset) {
        this.move(offset.x, offset.y);
    }

    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

    public void setCenter(int x, int y) {
        this.position.x = x - this.size.x / 2;
        this.position.y = y - this.size.y / 2;
    }

    public void setCenter(Vector2i center) {
        this.setCenter(center.x, center.y);
    }

    public void setSize(int sizeX, int sizeY) {
        this.size.x = sizeX;
        this.size.y = sizeY;
    }
}
