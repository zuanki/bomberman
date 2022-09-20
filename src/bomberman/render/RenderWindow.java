package bomberman.render;

import bomberman.utilities.Vector2i;

public class RenderWindow {
    private Vector2i size;
    private View view;

    public RenderWindow() {
    }

    public View getDefaultView() {
        return new View(new Vector2i(0, 0), this.size);
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setSize(int x, int y) {
        this.size.x = x;
        this.size.y = y;
    }
}
