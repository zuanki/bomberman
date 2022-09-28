package bomberman.state;

import bomberman.BombermanGame;
import javafx.scene.Parent;

public abstract class State {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 13;
    protected Parent root;
    protected BombermanGame game;

    public Parent getRoot() {
        return root;
    }

    public abstract void update();

    public abstract void enter();

    public abstract void exit();

    public abstract void render();
}
