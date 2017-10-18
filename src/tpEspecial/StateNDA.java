package tpEspecial;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dwape on 10/6/17.
 */
public class StateNDA implements State{

    private final List<State> transitions;

    private final List<Character> keys;

    private boolean acceptance;

    private String label;

    public StateNDA(){
        transitions = new ArrayList<>();
        keys = new ArrayList<>();
        this.acceptance = false;
    }

    public State transition(char character){
        return null;
    }

    public boolean addTransition(State transition, char character){
        transitions.add(transition);
        keys.add(character);
        return true;
    }

    public List<State> transitions(char character){
        ArrayList<State> list = new ArrayList<>();
        for (int i=0; i<transitions.size(); i++){
            if (keys.get(i) == character) list.add(transitions.get(i));
        }
        return list;
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

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public List<State> getTransitions() {
        return transitions;
    }

    public List<Character> getKeys() {
        return keys;
    }
}
