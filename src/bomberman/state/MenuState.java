package bomberman.state;

import bomberman.BombermanGame;
import bomberman.audio.Sound;
import bomberman.controls.MenuController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MenuState extends State {

    private final Sound menuSound = new Sound("/audio/menu.wav", false);

    public MenuState(BombermanGame game) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
        root = loader.load();
        MenuController menuController = loader.getController();
        menuController.setBombermanGame(game);
        super.game = game;
    }

    @Override
    public void update() {
        //
    }

    @Override
    public void enter() {
        if (!this.game.getAudioState()) {
            menuSound.play();
        }
    }

    @Override
    public void exit() {
        menuSound.stop();
    }

    @Override
    public void render() {

    }
}
