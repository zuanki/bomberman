package bomberman.controls;

import bomberman.BombermanGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    private BombermanGame bombermanGame;

    private boolean audioState = false;

    @FXML
    private Button audioControl;

    public MenuController() {
        //
    }

    public void setBombermanGame(BombermanGame bombermanGame) {
        this.bombermanGame = bombermanGame;
    }


    public void menuButtonLevel2(ActionEvent actionEvent) {
        this.bombermanGame.setLevel(2);
        this.bombermanGame.changeState("playing");
    }

    public void menuButtonLevel3(ActionEvent actionEvent) {
        this.bombermanGame.setLevel(3);
        this.bombermanGame.changeState("playing");
    }

    public void menuButtonLevel4(ActionEvent actionEvent) {
        this.bombermanGame.setLevel(4);
        this.bombermanGame.changeState("playing");
    }

    public void menuButtonLevel1(ActionEvent actionEvent) {
        this.bombermanGame.setLevel(1);
        this.bombermanGame.changeState("playing");
    }

    public void menuButtonHelp(ActionEvent actionEvent) {
        this.bombermanGame.changeState("helpingstate");
    }

    public void userAudio(ActionEvent actionEvent) {
        if (this.audioControl.getText().equals("\uD83D\uDD0A")) {
            this.bombermanGame.setAudio(true);
            this.audioControl.setText("\uD83D\uDD07");
        } else {
            this.bombermanGame.setAudio(false);
            this.audioControl.setText("\uD83D\uDD0A");
        }
    }
}
