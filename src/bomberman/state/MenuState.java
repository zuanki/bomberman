package bomberman.state;

import bomberman.BombermanGame;
import bomberman.inputs.KeyPolling;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class MenuState extends State {

    public MenuState(BombermanGame game) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
        root = loader.load();
        super.game = game;
    }

    @Override
    public void update() {
        if (KeyPolling.getInstance().isKeyDown(KeyCode.Y)) {
            this.game.changeState("gamestate");
            System.out.println("Pressed Y");
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

    }
}
