package bomberman.map;

import bomberman.entities.Bomber;
import bomberman.entities.Grass;
import bomberman.entities.Wall;
import bomberman.graphics.Sprite;
import bomberman.render.RenderWindow;
import bomberman.system.EntitiesManager;
import bomberman.system.PhysicSystem;
import bomberman.utilities.Util;

public class TileMap {
    public static final int CELL_SIZE = Sprite.SCALED_SIZE;
    private int rows;
    private int cols;
    private EntitiesManager entitiesManager = new EntitiesManager();
    private PhysicSystem physicSystem = new PhysicSystem();
    private Bomber bomber;

    public TileMap() {
        LevelReader reader = new LevelReader(this);
        reader.read(2);
        this.bomber = new Bomber(1, 1, Sprite.player_down.getFxImage());
        this.entitiesManager.add(this.bomber);
        this.physicSystem.add(this.bomber);
    }

    public void update() {
        this.entitiesManager.update();
        this.physicSystem.update();
    }

    public void render(RenderWindow renderWindow) {
        int m = (this.bomber.getX() - renderWindow.getWidth() / 2);
        int n = (this.bomber.getY() - renderWindow.getHeight() / 2);

        renderWindow.setOffSetX(Util.clamp(m, 0, cols * CELL_SIZE - renderWindow.getWidth()));
        renderWindow.setOffSetY(Util.clamp(n, 0, rows * CELL_SIZE - renderWindow.getHeight()));
        this.entitiesManager.render(renderWindow);
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
        Wall wall = new Wall(xUnit, yUnit);
        this.entitiesManager.add(wall);
        this.physicSystem.add(wall);
    }

    public void addGrass(int xUnit, int yUnit) {
        this.entitiesManager.add(new Grass(xUnit, yUnit));
    }
}
