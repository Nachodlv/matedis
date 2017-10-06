package tpEspecial;

import java.util.List;

/**
 * Created by Dwape on 10/6/17.
 */
public class DetermineAutomaton {

    public static State determine(State automaton){
        State deterministic = new StateImpl(false);
        determine(automaton, deterministic, automaton);

        return deterministic;
    }

    private static void determine(State state, State deterministic, State head){
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

    public static StateNDA merge(List<State> list){
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
    }

    public static State convert(State automaton){

    }
}
