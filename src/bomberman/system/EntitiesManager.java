package bomberman.system;

import bomberman.entities.Entity;
import bomberman.render.RenderWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntitiesManager {
    private List<Entity> entities = new ArrayList<>();

    public void update() {
        //
        for (int i = this.entities.size() - 1; i >= 0; i--) {
            this.entities.get(i).update();
        }

        entities = entities.stream().filter(Entity::isActive).collect(Collectors.toList());
    }

    public void render(RenderWindow renderWindow) {
        for (Entity entity : this.entities) {
            entity.render(renderWindow);
        }
    }

    public void add(Entity entity) {
        this.entities.add(entity);
    }
}
