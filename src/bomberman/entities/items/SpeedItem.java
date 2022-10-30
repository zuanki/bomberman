package bomberman.entities.items;

import bomberman.audio.Sound;
import bomberman.entities.Bomber;
import bomberman.entities.Entity;
import bomberman.graphics.Sprite;
import bomberman.map.TileMap;

public class SpeedItem extends Entity {
    private final TileMap tileMap;

    private Sound item = new Sound("/audio/item.wav", false);

    public SpeedItem(int xUnit, int yUnit, int layer, TileMap tileMap) {
        super(xUnit, yUnit, Sprite.powerup_speed.getFxImage(), layer);
        this.tileMap = tileMap;
    }

    @Override
    public void onCollision(Entity other) {
        if (this.tileMap.getEntityAt(this.getColIndex(), this.getRowIndex()) == null) {
            if (other instanceof Bomber bomber) {
                this.setActive(false);
                if (!this.tileMap.isTurnOffAudio()) {
                    this.item.play();
                }
                bomber.increaseSpeed();
            }
        }
    }

    @Override
    public void update() {
        //
    }
}
