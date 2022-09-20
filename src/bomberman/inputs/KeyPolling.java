package bomberman.inputs;

import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

public class KeyPolling {
    private static KeyPolling instance;
    private final Set<KeyCode> downKeys = new HashSet<>();

    private KeyPolling() {

    }

    public static KeyPolling getInstance() {
        if (instance == null) {
            instance = new KeyPolling();
        }
        return instance;
    }

    public void addKey(KeyCode code) {
        downKeys.add(code);
    }

    public void removeKey(KeyCode code) {
        downKeys.remove(code);
    }

    public boolean isKeyDown(KeyCode code) {
        return downKeys.contains(code);
    }
}
