package tpEspecial;

import java.util.*;

/**
 * Created by Dwape on 10/6/17.
 */
public class DetermineAutomaton {

    public static  StateImpl determine(StateNDA automaton){
        List<Character> keys =  automaton.getKeys();
        List<State> transitions = automaton.getTransitions();
        StateImpl deterministic = merge(keys, transitions); //q0
        deterministic.setLabel("0");

        determine(deterministic, deterministic);

        return deterministic;
    }

    private static StateImpl merge(List<Character> keys , List<State> transitions){
        StateImpl newState = new StateImpl(false);
        for (int i = 0; i < keys.size(); i++) {
            Character key = keys.get(i);
            List<State> states = new ArrayList<>();
            states.add(transitions.get(i));
            for (int j = 0; j < keys.size(); j++) {
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
        if(stateB.isAcceptance())((StateImpl)stateA).setAcceptance(((StateImpl)stateB).getAcceptanceWords());
        for(Map.Entry<Character,State> entry: ((StateImpl)stateB).getTransitions().entrySet()) {
            boolean result = stateA.addTransition(entry.getValue(), entry.getKey());
            if (!result){
                addTransitions(stateA.transition(entry.getKey()), entry.getValue());
            }
        }
    }

    private static void determine(StateImpl state, State head){
        Queue<StateImpl> queue = new LinkedList<>();
        List<StateImpl> visited = new ArrayList<>();
        queue.add(state);
        while (!queue.isEmpty()){
            StateImpl currentState = queue.remove();
            if(!visited.contains(currentState)) {
                addTransitions(currentState, head);
                visited.add(currentState);
                for (Map.Entry<Character, State> entry : currentState.getTransitions().entrySet()) {
                    queue.add((StateImpl) entry.getValue());
                }
            }
        }

    }

}
