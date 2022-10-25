package bomberman.state;

import bomberman.BombermanGame;
import bomberman.graphics.Sprite;
import bomberman.map.LevelReader;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameState extends State {

    private controlGame controlGame ;


    private Label label = new Label();
    private final TileMap map = new TileMap();
    private LevelReader levelReader = new LevelReader(map);

    private RenderWindow renderWindow;
    public GameState(BombermanGame game, int state) throws IOException {

        levelReader.read(state);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/playing"+Integer.toString(state)+".fxml"));
        root = loader.load();
        controlGame = loader.getController();
        canvas = (Canvas) root.lookup("#canvas");;
        this.renderWindow = new RenderWindow(canvas);
        this.renderWindow.setWidth(Sprite.SCALED_SIZE * WIDTH );
        this.renderWindow.setHeight(Sprite.SCALED_SIZE * HEIGHT);
        super.game = game;
    }

    @Override
    public Canvas getCanvas() {
        return super.getCanvas();
    }


    @Override
    public void update()  {

        this.map.update();
        controlGame.update();
        if (controlGame.startGame1) {
            this.game.changeState(TileMap.NAMESTATE);
        }
    }


    @Override
    public void enter() {

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
