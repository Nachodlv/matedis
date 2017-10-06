package tpEspecial;

import java.util.List;

/**
 * Created by Gianni on 10/6/2017.
 */
public class CreateAutomata {
    State head;
    List<String> phrases;

    public CreateAutomata(List<String> phrases) {
        this.phrases = phrases;
        for (int i = 0; i < phrases.size(); i++) {
            addPhrase(phrases.get(i));
        }
    }

    public void addPhrase(String phrase){
        State state = head;
        for (int i = 0; i < phrase.length(); i++) {
            char character = phrase.charAt(i);
            final State transition = state.transitions(character);
            state.addTransition(transition, character);
            state=transition;
        }
    }
}
