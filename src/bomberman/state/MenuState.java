package bomberman.state;

import bomberman.BombermanGame;
import bomberman.map.TileMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;


public class MenuState extends State{
    public MenuState(BombermanGame game, String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/"+ name +".fxml"));
        root = loader.load();
        canvas = (Canvas) root.lookup("#canvas");
        String imagePath = "C:\\Users\\phanx\\Downloads\\bomberman-master\\bomberman-master\\res\\sprites\\test.jpg";
        Image image = new Image(imagePath);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getHeight());

//        mediaView = (MediaView) root.lookup("#mediaView");
//        File f = new File("res/sounds/test.mp4");
//        Media m = new Media(f.toURI().toString());
//        MediaPlayer mp = new MediaPlayer(m);
//        mediaView.setMediaPlayer(mp);
//        mp.play();
     //   mp.play();

        super.game = game;

    }

    @Override
    public void update() {

        if (controlMenu.startGame) {
            this.game.changeState(TileMap.NAMESTATE);
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
