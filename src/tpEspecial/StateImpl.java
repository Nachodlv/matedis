package tpEspecial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dwape on 10/6/17.
 */
public class StateImpl implements State {

    private final HashMap<Character, State> transitions;

    private boolean acceptance;
    private List<String> acceptanceWords;

    private String label;

    public StateImpl(boolean acceptance){
        transitions = new HashMap<>();
        this.acceptance = acceptance;
        acceptanceWords = new ArrayList<>();
    }

    public State transition(char character){
        return transitions.get(character);
    }

    public List<State> transitions(char character){
        ArrayList<State> list = new ArrayList<>();
        list.add(transition(character));
        return list;
    }


    public boolean addTransition(State transition, char character){
        if(transitions.containsKey(character)) return false;
        transitions.put(character, transition);
        return true;
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

    public void setAcceptance(List<String> acceptanceWords) {
        this.acceptance = true;
        for(String word:acceptanceWords){
            if(!this.acceptanceWords.contains(word)){
                this.acceptanceWords.add(word);
            }
        }
    }

    public List<String> getAcceptanceWords() {
        return acceptanceWords;
    }

    public HashMap<Character, State> getTransitions() {
        return transitions;
    }
}
