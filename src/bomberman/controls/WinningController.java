package bomberman.controls;

import bomberman.BombermanGame;


public class WinningController {

    private BombermanGame game;

    public void setBombermanGame(BombermanGame game) {
        this.game = game;
    }

    public void returnMenu() {
        this.game.changeState("menustate");
    }
}
