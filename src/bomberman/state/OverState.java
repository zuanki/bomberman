package bomberman.state;

import bomberman.BombermanGame;
import bomberman.control.controlMenu;
import bomberman.control.controlOver;
import bomberman.map.TileMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;


public class OverState extends State{
    public OverState(BombermanGame game, String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/"+ "overstate" +".fxml"));
        root = loader.load();
        canvas = (Canvas) root.lookup("#canvas");
        String imagePath = "images\\gameover1.png";

        Image image = new Image(imagePath);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getHeight());

        super.game = game;

    }

    @Override
    public void update() {
        if(controlOver.ButtonOver){
            this.game.changeState("menustate");

        }
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
