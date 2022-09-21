package bomberman.system;

import bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhysicSystem {
    private List<Entity> entities = new ArrayList<>();

    public void add(Entity entity) {
        this.entities.add(entity);
    }

    public void update() {
        for (int i = 0; i < this.entities.size(); i++) {
            for (int j = 0; j < i; ++j) {
                if (this.entities.get(i).intersects(this.entities.get(j))) {
                    this.entities.get(i).onCollision(this.entities.get(j));
                    this.entities.get(j).onCollision(this.entities.get(i));
                }
            }
        }
        entities = entities.stream().filter(Entity::isActive).collect(Collectors.toList());
    }
}
