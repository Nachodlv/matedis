package tpEspecial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dwape on 10/6/17.
 */
public class StateNDA implements State{

    private final List<State> transitions;

    private final List<Character> keys;

    private final boolean acceptance;

    public StateNDA(boolean acceptance){
        transitions = new ArrayList<>();
        keys = new ArrayList<>();
        this.acceptance = acceptance;
    }

    public State transition(char character){
        return null;
    }

    public void addTransition(State transition, char character){
        transitions.add(transition);
        keys.add(character);
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
}
