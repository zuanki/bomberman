package bomberman.controls;

import bomberman.BombermanGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PlayingController {

    @FXML
    private Label playingTime;

    @FXML
    private Label playingScore;

    @FXML
    private Label playingBomb;

    @FXML

    private Button stateGame;

    private BombermanGame game;

    public void setBombermanGame(BombermanGame game) {
        this.game = game;
    }

    public void setTime(int time) {
        playingTime.setText("" + time);
    }

    public void setScore(int score) {
        playingScore.setText("" + score);
    }

    public void setBomb(int bomb) {
        playingBomb.setText("" + bomb);
    }

    public void returnMenu(ActionEvent actionEvent) {
        this.game.changeState("menustate");
    }

    public void userPause(ActionEvent actionEvent) {
        if (this.stateGame.getText().equals("⏸")) {
            this.stateGame.setText("▶");

        } else {
            this.stateGame.setText("⏸");

        }
    }

    public boolean getStateUpdate() {
        return this.stateGame.getText().equals("⏸");
    }

    public void reset() {
        this.stateGame.setText("⏸");
    }
}
