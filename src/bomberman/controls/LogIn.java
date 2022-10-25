package bomberman.controls;

import bomberman.BombermanGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogIn {

    @FXML
    private Button button;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private BombermanGame bombermanGame;


    public LogIn() {
        //
    }

    private void checkLogIn() {
        if (username.getText().equals("admin") && password.getText().equals("admin")) {
            this.bombermanGame.changeState("playing1");
        }
    }

    public void setBombermanGame(BombermanGame bombermanGame) {
        this.bombermanGame = bombermanGame;
    }

    public void userLogIn(ActionEvent actionEvent) {
        this.checkLogIn();

    }

    public void userEasyLevel() {
        this.bombermanGame.changeState("playing1");
    }

    public void userMediumLevel() {
        this.bombermanGame.changeState("playing2");
    }

    public void userHardLevel() {
        //
    }
}
