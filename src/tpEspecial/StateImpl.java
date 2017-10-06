package tpEspecial;

import java.util.HashMap;

/**
 * Created by Dwape on 10/6/17.
 */
public class StateImpl implements State {

    private final HashMap<Character, State> transitions;

    private final boolean acceptance;

    public StateImpl(boolean acceptance){
        transitions = new HashMap<>();
        this.acceptance = acceptance;
    }

    public State transition(char character){
        return transitions.get(character);
    }

    public void addTransition(State transition, char character){
        transitions.put(character, transition);
    }

    public boolean isAcceptance(){
        return acceptance;
    }
}
