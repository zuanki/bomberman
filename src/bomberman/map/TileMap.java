package bomberman.map;

import bomberman.entities.Bomber;
import bomberman.entities.Entity;
import bomberman.entities.Grass;
import bomberman.entities.Wall;
import bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class TileMap {
    private int rows;
    private int cols;
    private List<Entity> entities = new ArrayList<>();

    public TileMap() {
        LevelReader reader = new LevelReader(this);
        reader.read(1);
        Entity bomberman = new Bomber(0, 0, Sprite.player_left.getFxImage());
        entities.add(bomberman);
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render(GraphicsContext graphicsContext) {
        entities.forEach(entity -> entity.render(graphicsContext));
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void addWall(int xUnit, int yUnit) {
        entities.add(new Wall(xUnit, yUnit));
    }

    public void addGrass(int xUnit, int yUnit) {
        entities.add(new Grass(xUnit, yUnit));
    }
}
