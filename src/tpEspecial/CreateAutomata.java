package tpEspecial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Gianni on 10/6/2017.
 */
public class CreateAutomata {

    public static StateNDA createAutomata(Map<String, Integer> phrases) {
        StateNDA head = new StateNDA();
        head.setLabel("0");
        int counter = 1;
        for (String word: phrases.keySet()) {
            counter = addPhrase(word,head, counter);
        }
        return head;
    }

    public static int addPhrase(String phrase, State head, int counter){
        State state = head;
        for (int i = 0; i < phrase.length(); i++) {
            char character = phrase.charAt(i);
            final StateImpl transition;
            if(i == phrase.length() - 1){
                transition= new StateImpl(true);
                List<String> word = new ArrayList<>();
                word.add(phrase);
                transition.setAcceptance(word);
            }else{
                transition = new StateImpl(false);
            }
            transition.setLabel(String.valueOf(counter));
            counter++;
            state.addTransition(transition, character);
            state=transition;
        }
        return counter;
    }
}
