package tpEspecial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dwape on 10/6/17.
 */
public class DetermineAutomaton {

    /*public static State determine(State automaton){
        State deterministic = new StateImpl(false);
        determine(automaton, deterministic, automaton);

        return deterministic;
    }*/

    /*private static void determine(State state, State deterministic, State head){
        for (int i=0; i<256; i++){
            List<State> list = state.transitions((char) i);
            if (list.size() != 0){
                list.add(state);
                //deterministic.addTransition(merge(list), (char) i), deterministic);
                StateNDA newState = merge(list);
                deterministic.addTransition(newState, (char) i);
                determine(newState, deterministic, head);
            }
        }
    }

    private static StateNDA merge(List<State> list){
        StateNDA state = new StateNDA();
        for (int i=0; i<list.size(); i++){
            for (int j=0; j<256; j++){
                State newState = list.get(i).transition((char) j);
                if (newState != null){
                    state.addTransition(newState, (char) j);
                }
            }
        }
        return state;
    }*/

    public static  StateImpl determine(StateNDA automaton){
        List<Character> keys =  automaton.getKeys();
        List<State> transitions = automaton.getTransitions();
        StateImpl deterministic = merge(keys, transitions); //q0
        deterministic.setLabel("0");
        for(Map.Entry<Character,State> entry: deterministic.getTransitions().entrySet()){
            determine((StateImpl) entry.getValue(), deterministic, entry.getKey());
        }

        return deterministic;
    }

    private static StateImpl merge(List<Character> keys , List<State> transitions){
        StateImpl newState = new StateImpl(false);
        for (int i = 0; i < keys.size(); i++) {
            Character key = keys.get(i);
            List<State> states = new ArrayList<>();
            states.add(transitions.get(i));
            for (int j = 0; j < keys.size(); j++) { //a-a-b
                if(j==i) continue;
                if(key == keys.get(j)) {
                    states.add(transitions.get(j));
                    keys.remove(j);
                    transitions.remove(j);
                    j--;
                }
            }
            if(states.size()>1){
                newState.addTransition(mergeStates(states), key);
            }else {
                newState.addTransition(transitions.get(i),key);
            }
            states.clear();
        }
        return newState;
    }

    private static State mergeStates(List<State> states){
        State state = states.get(0);
        for (int i = 1; i < states.size(); i++) {
            addTransitions(state, states.get(i));
        }
        return state;
    }

    private static void addTransitions(State stateA, State stateB){
        if(stateA.equals(stateB)) return;
        if(stateB.isAcceptance()) ((StateImpl)stateA).setAcceptance(((StateImpl)stateB).getAcceptanceWords());
        for(Map.Entry<Character,State> entry: ((StateImpl)stateB).getTransitions().entrySet()) {
            boolean result = stateA.addTransition(entry.getValue(), entry.getKey());
            if (!result){
                addTransitions(stateA.transition(entry.getKey()), entry.getValue());
            }
        }
    }

    private static void determine(StateImpl state, State head, char character){
        for(Map.Entry<Character,State> entry: state.getTransitions().entrySet()){
            determine((StateImpl) entry.getValue(), head, entry.getKey());
        }
        addTransitions(state, head);
    }





   /* private static State determine(State automaton, StateNDA head, StateImpl deterministic){
        HashMap<Character, State> transitions = getTransitions(automaton, head);
        StateImpl newState = new StateImpl();

    }

    private static HashMap<Character, State> addTransitions(StateImpl automaton, S head){
        HashMap<Character, State> list = ((StateImpl) automaton).getTransitions();
        for (int i = 0; i < head.getKeys().size(); i++) {
           automaton.addTransition(head.getTransitions().get(i), head.getKeys().get(i));
        }
        return list;
    }*/



}
