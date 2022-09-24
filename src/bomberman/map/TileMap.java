package bomberman.map;

import bomberman.entities.*;
import bomberman.entities.enemies.Balloom;
import bomberman.graphics.Sprite;
import bomberman.render.RenderWindow;
import bomberman.system.EntitiesManager;
import bomberman.system.PhysicSystem;
import bomberman.utilities.Util;

public class TileMap {
    public static final int CELL_SIZE = Sprite.SCALED_SIZE;
    public static final int GRASS_LAYER = -1;
    public static final int WALL_LAYER = 1;
    public static final int BOMBER_LAYER = 100;
    public static final int BOMB_LAYER = 1;
    public static final int FLAME_LAYER = 1;
    public static final int ANIMATION_LAYER = 2;
    public static final int BRICK_LAYER = 1;
    public static final int ENEMY_LAYER = 1;
    private final EntitiesManager entitiesManager = new EntitiesManager();
    private final PhysicSystem physicSystem = new PhysicSystem();
    public int levelFlame = 2;
    private Bomber bomber;
    private int rows;
    private int cols;
    private Entity[][] board;

    public TileMap() {
        LevelReader reader = new LevelReader(this);
        reader.read(1);
    }

    public void addBomber(int xUnit, int yUnit) {
        this.bomber = new Bomber(xUnit, yUnit, Sprite.player_down.getFxImage(), this, BOMBER_LAYER);
        this.entitiesManager.add(this.bomber);
        this.physicSystem.add(this.bomber);
    }

    public Entity getEntityAt(int xUnit, int yUnit) {
        return this.board[yUnit][xUnit];
    }

    public Entity[][] getBoard() {
        return board;
    }

    public void update() {
        this.entitiesManager.update();
        this.physicSystem.update();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (this.board[i][j] != null && !this.board[i][j].isActive()) {
                    this.board[i][j] = null;
                }
            }
        }
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

    public void setDimension(int height, int width) {
        this.rows = height;
        this.cols = width;
        this.board = new Entity[rows][cols];
    }

    public Bomber getBomber() {
        return bomber;
    }

    public int getCols() {
        return cols;
    }


    public void addWall(int xUnit, int yUnit) {
        Wall wall = new Wall(xUnit, yUnit, WALL_LAYER);
        this.board[yUnit][xUnit] = wall;
        this.entitiesManager.add(wall);
        this.physicSystem.add(wall);
    }

    public void addBrick(int xUnit, int yUnit) {
        Brick brick = new Brick(xUnit, yUnit, BRICK_LAYER);
        this.board[yUnit][xUnit] = brick;
        this.entitiesManager.add(brick);
        this.physicSystem.add(brick);
    }

    public void addGrass(int xUnit, int yUnit) {
        Grass grass = new Grass(xUnit, yUnit, GRASS_LAYER);
        //this.board[yUnit][xUnit] = grass;
        this.entitiesManager.add(grass);
    }

    public void addBomb(int xUnit, int yUnit) {
        /*if (this.board[yUnit][xUnit] != null) {
            System.out.println("Already have bomb");
        }*/
        if (this.board[yUnit][xUnit] == null) {
            Bomb bomb = new Bomb(xUnit, yUnit, this, BOMB_LAYER);
            this.entitiesManager.add(bomb);
            this.board[yUnit][xUnit] = bomb;
            this.physicSystem.add(bomb);
        }
    }

    public void addFlame(int xUnit, int yUnit, Flame.Type type) {
        Flame flame = new Flame(xUnit, yUnit, type, FLAME_LAYER);
        this.entitiesManager.add(flame);
    }

    public void addAnimation(Sprite a, Sprite b, Sprite c, int xUnit, int yUnit) {
        this.entitiesManager.add(new UAnimation(a, b, c, xUnit, yUnit, ANIMATION_LAYER));
    }

    public void addEnemyBalloom(int xUnit, int yUnit) {
        this.entitiesManager.add(new Balloom(xUnit, yUnit, this));
    }
}
