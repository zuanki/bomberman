package bomberman.state;

import bomberman.BombermanGame;
import bomberman.controls.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.Objects;

public class MenuState extends State {
    private final String path = "/audio/menu.wav";
    private MediaPlayer mediaPlayer;

    public MenuState(BombermanGame game) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
        root = loader.load();
        Media sound = new Media(Objects.requireNonNull(getClass().getResource(path)).toString());
        this.mediaPlayer = new MediaPlayer(sound);
        MenuController menuController = loader.getController();
        menuController.setBombermanGame(game);
        menuController.setMediaPlayer(this.mediaPlayer);
        super.game = game;
    }

    @Override
    public void update() {
        //
    }

    @Override
    public void enter() {
        this.mediaPlayer.play();
    }

    @Override
    public void exit() {
        this.mediaPlayer.stop();
    }

    @Override
    public void render() {

    }
}
