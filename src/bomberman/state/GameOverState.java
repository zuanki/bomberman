package bomberman.state;

import bomberman.BombermanGame;
import bomberman.controls.WinningController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GameOverState extends State {
    public GameOverState(BombermanGame game) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/gameover.fxml"));
        root = loader.load();
        WinningController winningController = loader.getController();
        winningController.setBombermanGame(game);
        super.game = game;
    }

    @Override
    public void update() {

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
