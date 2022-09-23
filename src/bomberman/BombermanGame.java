package bomberman;

import bomberman.graphics.Sprite;
import bomberman.inputs.KeyPolling;
import bomberman.map.TileMap;
import bomberman.render.RenderWindow;
import bomberman.system.GameLoop;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class BombermanGame extends Application {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 13;

    private final TileMap map = new TileMap();
    private Canvas canvas;
    private RenderWindow renderWindow;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        this.canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        this.renderWindow = new RenderWindow(canvas);
        this.renderWindow.setWidth(Sprite.SCALED_SIZE * WIDTH);
        this.renderWindow.setHeight(Sprite.SCALED_SIZE * HEIGHT);
        Group root = new Group();
        root.getChildren().add(this.canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bomberman");
        stage.getIcons().add(Sprite.bomb.getFxImage());
        stage.show();
        // Input
        scene.setOnKeyPressed(keyEvent -> KeyPolling.getInstance().addKey(keyEvent.getCode()));
        scene.setOnKeyReleased(keyEvent -> KeyPolling.getInstance().removeKey(keyEvent.getCode()));
        //
        GameLoop gameLoop = new GameLoop(this);
        gameLoop.start();
    }

    public void update() {
        map.update();
    }

    public void render() {
        this.renderWindow.clear();
        map.render(this.renderWindow);
    }
}
