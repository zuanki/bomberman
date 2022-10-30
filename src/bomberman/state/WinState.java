package bomberman.state;

import bomberman.BombermanGame;
import bomberman.audio.Sound;
import bomberman.controls.WinningController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class WinState extends State {

    private Sound winSound = new Sound("/audio/win.wav", false);

    public WinState(BombermanGame game) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/win.fxml"));
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
        if (!this.game.getAudioState()) {
            this.winSound.play();
        }
    }

    @Override
    public void exit() {
        this.winSound.stop();
    }

    @Override
    public void render() {

    }
}
