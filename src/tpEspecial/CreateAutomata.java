package tpEspecial;

import java.util.List;

/**
 * Created by Gianni on 10/6/2017.
 */
public class CreateAutomata {

    public static State createAutomata(List<String> phrases) {
        StateNDA head = new StateNDA(false);
        for (int i = 0; i < phrases.size(); i++) {
            addPhrase(phrases.get(i),head);
        }
        return head;
    }

    public static void addPhrase(String phrase, State head){
        State state = head;
        for (int i = 0; i < phrase.length(); i++) {
            char character = phrase.charAt(i);
            final State transition = new StateImpl(i==phrase.length()-1);
            state.addTransition(transition, character);
            state=transition;
        }
    }
}
