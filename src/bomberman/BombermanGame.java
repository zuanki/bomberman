package bomberman;

import bomberman.graphics.Sprite;
import bomberman.inputs.KeyPolling;
import bomberman.map.TileMap;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class BombermanGame extends Application {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 13;
    private final TileMap map = new TileMap();
    private GraphicsContext graphicsContext;
    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);

        // Input
        scene.setOnKeyPressed(keyEvent -> KeyPolling.getInstance().addKey(keyEvent.getCode()));
        scene.setOnKeyReleased(keyEvent -> KeyPolling.getInstance().removeKey(keyEvent.getCode()));

        // Add scene vao stage
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
    }

    public void update() {
        map.update();
    }

    public void render() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.render(graphicsContext);
    }
}
