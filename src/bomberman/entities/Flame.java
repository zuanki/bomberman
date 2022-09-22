package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.render.RenderWindow;


public class Flame extends Entity {
    public Type type;

    public Flame(int xUnit, int yUnit, Type type, int layer) {
        super(xUnit, yUnit, null, layer);
        this.type = type;
    }

    @Override
    public void render(RenderWindow renderWindow) {
        long time = System.currentTimeMillis();
        Sprite sprite = switch (type) {
            case HORIZONTAL ->
                    Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, time, 400);
            case HORIZONTAL_LEFT ->
                    Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, time, 400);
            case HORIZONTAL_RIGHT ->
                    Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, time, 400);
            case VERTICAL ->
                    Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, time, 400);
            case VERTICAL_TOP ->
                    Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, time, 400);
            case VERTICAL_DOWN ->
                    Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, time, 400);
            case CENTER ->
                    Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, time, 400);
        };
        renderWindow.render(sprite.getFxImage(), this.x, this.y);
    }

    @Override
    public void update() {

    }

    public enum Type {
        HORIZONTAL, HORIZONTAL_LEFT, HORIZONTAL_RIGHT, VERTICAL, VERTICAL_TOP, VERTICAL_DOWN, CENTER;
    }
}
