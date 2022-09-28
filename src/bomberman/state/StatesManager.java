package bomberman.state;

import java.util.HashMap;

public class StatesManager {
    private final HashMap<String, State> states = new HashMap<>();
    private String currentState = "";

    public void addState(String name, State newState) {
        this.states.put(name, newState);

    }

    public void removeState(String name) {
        this.states.remove(name);
    }

    public void changeState(String name) {
        if (currentState.equals(name)) {
            return;
        }

        if (getCurrentState() != null) {
            getCurrentState().exit();
        }

        currentState = name;

        getCurrentState().enter();
    }

    public State getCurrentState() {
        return this.states.get(currentState);
    }
}
