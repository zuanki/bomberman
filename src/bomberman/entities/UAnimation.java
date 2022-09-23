package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.render.RenderWindow;

public class UAnimation extends Entity{
    private Sprite a;
    private Sprite b;
    private Sprite c;
    public UAnimation(Sprite a, Sprite b, Sprite c, int xUnit, int yUnit, int layer) {
        super(xUnit, yUnit, null, layer);
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public void render(RenderWindow renderWindow) {
        renderWindow.render(Sprite.movingSprite(a, b, c, (long) System.currentTimeMillis(), 400).getFxImage(),
                this.x, this.y);
    }
    @Override
    public void update() {

    }
}
