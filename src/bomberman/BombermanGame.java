package bomberman;

import bomberman.graphics.Sprite;
import bomberman.inputs.KeyPolling;
import bomberman.state.*;
import bomberman.system.GameLoop;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BombermanGame extends Application {
    private final StatesManager statesManager = new StatesManager();
    private Scene scene;

    private boolean turnOffAudio = false;

    private int level = 1;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {

//        this.canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        //stage.setHeight(506);
        //stage.setWidth(490);
        initStates();
        scene = new Scene(statesManager.getCurrentState().getRoot());
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

    private void initStates() {
        try {
            statesManager.addState("playing", new GameState(this));
            statesManager.addState("menustate", new MenuState(this));
            statesManager.addState("winstate", new WinState(this));
            statesManager.addState("gameover", new GameOverState(this));
            statesManager.addState("helpingstate", new HelpingState(this));
        } catch (IOException e) {
            System.out.println("cannot instantiate states");
            Platform.exit();
        }
        this.statesManager.changeState("menustate");
    }

    public void update() {

        statesManager.getCurrentState().update();

    }

    public void render() {
        statesManager.getCurrentState().render();
    }

    public void changeState(String name) {
        statesManager.changeState(name);
        scene.setRoot(this.statesManager.getCurrentState().getRoot());
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAudio(boolean x) {
        this.turnOffAudio = x;
    }

    public boolean getAudioState() {
        return this.turnOffAudio;
    }
}
