package tpEspecial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dwape on 10/6/17.
 */
public class StateImpl implements State {

    private final HashMap<Character, State> transitions;

    private final boolean acceptance;

    private String label;

    public StateImpl(boolean acceptance){
        transitions = new HashMap<>();
        this.acceptance = acceptance;
    }

    public State transition(char character){
        return transitions.get(character);
    }

    public List<State> transitions(char character){
        ArrayList<State> list = new ArrayList<>();
        list.add(transition(character));
        return list;
    }

    public void addTransition(State transition, char character){
        transitions.put(character, transition);
    }

    public boolean isAcceptance(){
        return acceptance;
    }

    public void setLabel(String label){
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public HashMap<Character, State> getTransitions() {
        return transitions;
    }
}
