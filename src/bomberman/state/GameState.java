package bomberman.state;

import bomberman.graphics.Sprite;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;

import java.io.IOException;

public class GameState extends State {

    private final TileMap map = new TileMap();
    private Canvas canvas;
    private RenderWindow renderWindow;

    public GameState() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/playing.fxml"));
        root = loader.load();
        this.canvas = (Canvas) root.lookup("#canvas");
        this.renderWindow = new RenderWindow(canvas);
        this.renderWindow.setWidth(Sprite.SCALED_SIZE * WIDTH);
        this.renderWindow.setHeight(Sprite.SCALED_SIZE * HEIGHT);
    }

    @Override
    public void update() {
        this.map.update();
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
