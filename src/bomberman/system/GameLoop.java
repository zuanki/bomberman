package bomberman.system;

import bomberman.BombermanGame;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private final double deltaTime = 1e9 / 60;
    private BombermanGame game;
    private long accumulator = 0;
    private long prev = System.nanoTime();

    public GameLoop(BombermanGame game) {
        this.game = game;
    }

    @Override
    public void handle(long now) {
        accumulator += now - prev;
        prev = now;
        while (accumulator >= deltaTime) {
            game.update();
            game.render();
            accumulator -= deltaTime;
        }
    }
}
