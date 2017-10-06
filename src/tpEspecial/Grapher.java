package tpEspecial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grapher {
    int label = 0;

    public void graphNDA(State automata){
        State currentAutomata = automata;
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter("src/tpEspecial/grafoNDA.txt"));
            bw.write("digraph{\nrankdir = \"LR\";\n");
            List<State> firstTransitions = ((StateNDA)automata).getTransitions();
            List<Character> firstKeys = ((StateNDA)automata).getKeys();
            bw.write(writeNode(automata));
            for (State firstTransition : firstTransitions) {
                bw.write(writeNode(firstTransition));
                bw.write(writeAllWords(firstTransition, ""));
            }

            for (int i = 0; i < firstTransitions.size(); i++) {
                bw.write(connectAllNodes(automata, firstTransitions.get(i), firstKeys.get(i), ""));
            }

            bw.write("}");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String connectAllNodes(State stateA, State stateB,  char connect, String sentence){
        for(Map.Entry<Character,State> entry: ((StateImpl)stateB).getTransitions().entrySet()){
            return connectAllNodes(stateB,entry.getValue(), entry.getKey(), sentence.concat(transition(stateA, stateB, connect)));
        }
        return sentence.concat(transition(stateA, stateB, connect));
    }

    private String writeAllWords(State state, String sentence){
        HashMap<Character, State> hashMap = ((StateImpl) state).getTransitions();
        for(State nextState: hashMap.values()){
            return writeAllWords(nextState, sentence.concat(writeNode(nextState)));
        }
         return sentence;
    }

    private String writeNode(State automata){
        automata.setLabel(String.valueOf(label));
        label++;
        if(automata.isAcceptance()){
            return "node[shape = doublecircle]" + automata.getLabel() + "[label=\"" + automata.getLabel() + "\"];\n";
        }else{
            return "node[shape = circle]" + automata.getLabel() + "[label=\"" + automata.getLabel() + "\"];\n";
        }
    }

    private String transition(State a, State b, Character character){
        return a.getLabel() + " -> " + b.getLabel() + " [label=\"" + character + "\"];\n";
    }
}
