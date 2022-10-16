package bomberman.algorithm.AI;

public class Heuristic {

    public static final int D = 10;

    public static final int D2 = 14;

    public static Vector2<Integer> getDelta(Vector2<Integer> source, Vector2<Integer> target) {
        int m = Math.abs(source.x - target.x);
        int n = Math.abs(source.y - target.y);
        return new Vector2<>(m, n);
    }

    public static int manhattan(Vector2<Integer> source, Vector2<Integer> target) {
        Vector2<Integer> delta = Heuristic.getDelta(source, target);
        return D * (delta.x + delta.y);
    }

    public static int euclidean(Vector2<Integer> source, Vector2<Integer> target) {
        Vector2<Integer> delta = Heuristic.getDelta(source, target);
        return (int) (D * Math.sqrt(delta.x * delta.x + delta.y * delta.y));
    }

    public static int octagonal(Vector2<Integer> source, Vector2<Integer> target) {
        Vector2<Integer> delta = Heuristic.getDelta(source, target);
        return D * (delta.x + delta.y) + (D2 - 2 * D) * Math.min(delta.x, delta.y);
    }
}
