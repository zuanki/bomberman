package bomberman.algorithm.AI;

import java.util.Objects;

public class Vector2<T extends Number> {
    public T x;
    public T y;

    public Vector2(T a, T b) {
        this.x = a;
        this.y = b;
    }

    @Override
    public boolean equals(Object obj) {
        Vector2 other = (Vector2) obj;
        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }
}
