package bomberman.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class Sound {
    private final MediaPlayer mediaPlayer;

    public Sound(String path, boolean isLoop) {
        this.mediaPlayer = new MediaPlayer(new Media(Objects.requireNonNull(Sound.class.getResource(path)).toString()));
        this.mediaPlayer.setAutoPlay(isLoop);
    }

    public void play() {
        new Thread(this.mediaPlayer::play).start();
    }

    public void stop() {
        this.mediaPlayer.stop();
    }
}
