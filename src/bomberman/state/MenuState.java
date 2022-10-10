package bomberman.state;

import bomberman.BombermanGame;
import bomberman.controls.LogIn;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MenuState extends State {

    public MenuState(BombermanGame game) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
        root = loader.load();
        LogIn logInController = loader.<LogIn>getController();
        logInController.setBombermanGame(game);
        super.game = game;
    }

    @Override
    public void update() {
        //
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
