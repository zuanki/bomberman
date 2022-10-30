package bomberman.state;

import bomberman.BombermanGame;
import bomberman.control.controlMenu;
import bomberman.map.TileMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;


public class MapState extends State{
    public MapState(BombermanGame game, String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/"+ name +".fxml"));
        root = loader.load();
        canvas = (Canvas) root.lookup("#canvas");
        String imagePath = "images\\nextstage1.png";

        Image image = new Image(imagePath);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getHeight());


        super.game = game;

    }

    public void run_(){
        this.timer ++;
    }
    @Override
    public void update() {

            run_();

        if(this.timer == 120){
            System.out.println("ghfdgkjdhfgu");
            this.game.changeState("playing2");
            TileMap.LEFT_MAP = false;
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
