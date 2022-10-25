package bomberman;

import bomberman.graphics.Sprite;
import bomberman.inputs.KeyPolling;
import bomberman.sound.Sound;
import bomberman.state.GameState;
import bomberman.state.LeftTState;
import bomberman.state.MenuState;
import bomberman.state.StatesManager;
import bomberman.system.GameLoop;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BombermanGame extends Application {
    private final StatesManager statesManager = new StatesManager();
    private final Sound sound = new Sound();
    private Scene scene;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Sound.play("sound");

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
            statesManager.addState("playing1", new GameState(this,1));
            statesManager.addState("menustate", new MenuState(this,"menu"));
            statesManager.addState("leftstate",new LeftTState(this,"leftstate"));
            statesManager.addState("playing2", new GameState(this,2));
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
}
