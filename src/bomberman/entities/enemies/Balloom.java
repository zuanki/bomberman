package bomberman.entities.enemies;

import bomberman.entities.Bomber;
import bomberman.entities.Direction;
import bomberman.entities.Entity;
import bomberman.entities.Flame;
import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import bomberman.sound.Sound;
import bomberman.utilities.Vector2i;

public class Balloom extends Enemy {
//    public void callGame() throws IOException {
//        //super();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/playing.fxml"));
//        controlGame controlGame = loader.getController();
//        controlGame.update();
//    }

    public Balloom(int xUnit, int yUnit, TileMap map) {
        super(xUnit, yUnit, map);
    }

    @Override
    public void render(RenderWindow renderWindow) {
        if (this.animationDir == Direction.LEFT) {
            renderWindow.render(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        } else if (this.animationDir == Direction.RIGHT) {
            renderWindow.render(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3,
                    (long) System.currentTimeMillis(), 400).getFxImage(), this.x, this.y);
        }
    }

    @Override
    protected Vector2i getNextMove() {
        /*List<Vector2i> freeCells = getFreeCell();
        if (freeCells.isEmpty()) {
            return new Vector2i(getRowIndex(), getColIndex());
        }
        int index = (int) (Math.random() * freeCells.size());
        return freeCells.get(index);*/
        int r = this.getRowIndex();
        int c = this.getColIndex();

        // Try to move current dir
        if (canMove(c + movingVector.x, r + movingVector.y)) {
            return new Vector2i(c + movingVector.x, r + movingVector.y);
            //return new Vector2i(0, 0);
        } else if (canMove(c - movingVector.x, r - movingVector.y)) {
            return new Vector2i(c - movingVector.x, r - movingVector.y);
            //return new Vector2i(0, 0);
        }

        return new Vector2i(c, r);
        //return new Vector2i(0, 0);
    }

    @Override
    public void onCollision(Entity other) {

            if (other instanceof Flame) {
                Sound.play_A("player death");
                this.setActive(false);
                TileMap.SCORE += 100;
                this.map.addAnimation(Sprite.balloom_dead, Sprite.balloom_dead, Sprite.balloom_dead, this.getColIndex(), this.getRowIndex());
            }
            if (other instanceof Bomber bomber) {

                bomber.die();
            }
        }



}
