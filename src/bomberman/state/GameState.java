package bomberman.state;

import bomberman.BombermanGame;
import bomberman.controls.PlayingController;
import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;

import java.io.IOException;

public class GameState extends State {

    public static final int MAX_TIMER = 200 * 60;
    private TileMap map;
    private Canvas canvas;
    private RenderWindow renderWindow;
    private PlayingController playingController;
    private BombermanGame game;
    private int time = MAX_TIMER;

    private int score = 0;

    private int timeDelayState = 120;

    public GameState(BombermanGame game) throws IOException {
        this.game = game;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/playing.fxml"));
        root = loader.load();
        playingController = loader.getController();
        playingController.setBombermanGame(game);
        this.canvas = (Canvas) root.lookup("#canvas");
        this.renderWindow = new RenderWindow(canvas);
        this.renderWindow.setWidth(Sprite.SCALED_SIZE * WIDTH);
        this.renderWindow.setHeight(Sprite.SCALED_SIZE * HEIGHT);
    }

    @Override
    public void update() {
        if (this.playingController.getStateUpdate()) {
            --time;
            this.map.update();
            this.playingController.setTime(time / 60);
            this.playingController.setScore(this.map.getScore());
            this.playingController.setBomb(this.map.getCurrentBomb());
            if (this.map.isGoToNextState() && --this.timeDelayState < 0) {
                this.game.changeState("gameover");
                this.timeDelayState = 120;
            }
        }
    }

    @Override
    public void enter() {
        this.map = new TileMap(game.getLevel(), game);
        this.playingController.reset();
    }

    @Override
    public void exit() {

    }

    @Override
    public void render() {
        this.renderWindow.clear();
        map.render(this.renderWindow);
    }
}
