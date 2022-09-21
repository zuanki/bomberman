package bomberman.utilities;

public class Util {
    public static int clamp(int x, int min, int max) {
        if (x >= min && x <= max) {
            return x;
        } else if (x < min) {
            return min;
        } else {
            return max;
        }
    }
}
