package bomberman.state;

import bomberman.BombermanGame;
import bomberman.audio.Sound;
import bomberman.controls.WinningController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GameOverState extends State {
    private Sound gameOver = new Sound("/audio/gameOver.wav", false);

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
        if (!this.game.getAudioState()) {
            this.gameOver.play();
        }
    }

    @Override
    public void exit() {
        this.gameOver.stop();
    }

    @Override
    public void render() {

    }
}
