package bomberman.state;

import bomberman.BombermanGame;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.MediaView;

public abstract class State {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 13;
    protected Parent root;
    protected BombermanGame game = new BombermanGame();
    protected Canvas canvas;
    protected MediaView mediaView = new MediaView();


    public Parent getRoot() {
        return root;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public MediaView getMediaView() {
        return mediaView;
    }

    public abstract void update() ;

    public abstract void enter();

    public abstract void exit();

    public abstract void render();
}
