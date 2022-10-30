package bomberman.entities.enemies;

import bomberman.algorithm.AStar;
import bomberman.entities.Bomber;
import bomberman.entities.Direction;
import bomberman.entities.Entity;
import bomberman.entities.Flame;
import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import bomberman.sound.Sound;
import bomberman.utilities.Vector2i;
import javafx.util.Pair;

import java.util.List;


public class Oneal extends Enemy {
    //private List<Pair<Integer, Integer>> listMoveAStar = new ArrayList<>();
    private final AStar aStar = new AStar();
    private final int radius = 3;

//    public void callGame() throws IOException {
//        //super();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/playing.fxml"));
//        controlGame controlGame = loader.getController();
//        controlGame.update();
//    }
    public Oneal(int xUnit, int yUnit, TileMap map) {
        super(xUnit, yUnit, map);
    }

    @Override
    public void render(RenderWindow renderWindow) {
        if (this.animationDir == Direction.LEFT) {
            renderWindow.render(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        } else if (this.animationDir == Direction.RIGHT) {
            renderWindow.render(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        }
    }

    @Override
    protected Vector2i getNextMove() {
        // Nguyen Hoa
        if (this.canDetectBomber()) {
            int playerRowPos = this.map.getBomber().getRowIndex();
            int playerColPos = this.map.getBomber().getColIndex();
            int[][] board = convertMap(this.map.getBoard());
            return aStar.nextMoveByAStar(board, new Pair<>(this.getRowIndex(),
                    this.getColIndex()), new Pair<>(playerRowPos, playerColPos));
        } else {
            List<Vector2i> freeCells = getFreeCell();
            if (freeCells.isEmpty()) {
                return new Vector2i(getRowIndex(), getColIndex());
            }
            int index = (int) (Math.random() * freeCells.size());
            return freeCells.get(index);
        }

    }

    @Override
    public void onCollision(Entity other) {

        if (other instanceof Flame) {
            Sound.play_A("loot");
            this.setActive(false);
         //   callGame();
            this.map.addAnimation(Sprite.oneal_dead, Sprite.oneal_dead, Sprite.oneal_dead, this.getColIndex(), this.getRowIndex());
        }
        if (other instanceof Bomber bomber) {

            bomber.die();
            }
        }

    private int[][] convertMap(Entity[][] entities) {
        int row = entities.length;
        int col = entities[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (entities[i][j] == null) {
                    res[i][j] = 1;
                } else {
                    res[i][j] = 0;
                }
            }
        }
        return res;
    }

    private boolean canDetectBomber() {
        int srcXPos = this.getColIndex();
        int srcYPos = this.getRowIndex();
        int destXPos = this.map.getBomber().getColIndex();
        int destYPos = this.map.getBomber().getRowIndex();
        return Math.abs(srcXPos - destXPos) <= this.radius && Math.abs(srcYPos - destYPos) <= this.radius;
    }
}

