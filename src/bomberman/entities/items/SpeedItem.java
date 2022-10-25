package bomberman.entities.items;

import bomberman.entities.Entity;
import bomberman.graphics.Sprite;
import bomberman.map.TileMap;

public class SpeedItem extends Entity {
    private TileMap tileMap;

    public SpeedItem(int xUnit, int yUnit, int layer, TileMap tileMap){
        super(xUnit,yUnit, Sprite.powerup_speed.getFxImage(),layer);
        this.tileMap = tileMap;
    }



    @Override
    public void update() {
        if(this.intersects_item(tileMap.getBomber())){
            TileMap.SPEED = 200;
            this.setActive(false);

        }
    }
}