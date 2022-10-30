package bomberman.state;

import bomberman.BombermanGame;
import bomberman.control.controlMenu;
import bomberman.map.TileMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;


public class MenuState extends State{
    private TileMap map = new TileMap();
    public MenuState(BombermanGame game) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
        root = loader.load();
        canvas = (Canvas) root.lookup("#canvas");
        String imagePath = "images\\menu.png";

        Image image = new Image(imagePath);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getHeight());
        super.game = game;

    }

    @Override
    public void update() {
        if (controlMenu.startGame) {
            this.game.changeState("playing1");
            controlMenu.startGame = false;
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
