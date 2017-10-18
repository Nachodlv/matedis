package tpEspecial;

import java.util.List;

/**
 * Created by Gianni on 10/6/2017.
 */
public class CreateAutomata {

    public static StateNDA createAutomata(List<String> phrases) {
        StateNDA head = new StateNDA();
        head.setLabel("0");
        int counter = 1;
        for (int i = 0; i < phrases.size(); i++) {
            counter = addPhrase(phrases.get(i),head, counter);
        }
        return head;
    }

    public static int addPhrase(String phrase, State head, int counter){
        State state = head;
        for (int i = 0; i < phrase.length(); i++) {
            char character = phrase.charAt(i);
            final State transition = new StateImpl(i==phrase.length()-1);
            transition.setLabel(String.valueOf(counter));
            counter++;
            state.addTransition(transition, character);
            state=transition;
        }
        return counter;
    }
}
