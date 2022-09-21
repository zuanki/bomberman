package bomberman.render;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RenderWindow {
    private int width;
    private int height;
    private int offSetX = 0;
    private int offSetY = 0;
    private GraphicsContext graphicsContext;

    public RenderWindow(Canvas canvas) {
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public int getOffSetX() {
        return offSetX;
    }

    public void setOffSetX(int offSetX) {
        this.offSetX = offSetX;
    }

    public int getOffSetY() {
        return offSetY;
    }

    public void setOffSetY(int offSetY) {
        this.offSetY = offSetY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void render(Image image, int x, int y) {
        this.graphicsContext.drawImage(image, x - offSetX, y - offSetY);
    }

    public void clear() {
        this.graphicsContext.clearRect(0, 0, this.width, this.height);
    }
}
