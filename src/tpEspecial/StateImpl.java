package tpEspecial;

import java.util.HashMap;

/**
 * Created by Dwape on 10/6/17.
 */
public class StateImpl implements State {

    private final HashMap<Character, State> transitions;

    public StateImpl(){
        transitions = new HashMap<>();
    }

    public State transitions(char character){
        return transitions.get(character);
    }

    public void addTransition(State transition, char character){
        transitions.put(character, transition);
    }
}
