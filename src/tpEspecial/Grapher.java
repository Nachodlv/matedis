package tpEspecial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grapher {
    //int label = 0;

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
                bw.write(writeAllNodes(firstTransition, ""));
            }

            for (int i = 0; i < firstTransitions.size(); i++) {
                bw.write(connectNodes(automata, firstTransitions.get(i), firstKeys.get(i), ""));
            }

            bw.write("}");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String connectNodes(State stateA, State stateB, char connect, String sentence){
        for(Map.Entry<Character,State> entry: ((StateImpl)stateB).getTransitions().entrySet()){
            return connectNodes(stateB,entry.getValue(), entry.getKey(), sentence.concat(transition(stateA, stateB, connect)));
        }
        return sentence.concat(transition(stateA, stateB, connect));
    }

    private String writeAllNodes(State state, String sentence){
        HashMap<Character, State> hashMap = ((StateImpl) state).getTransitions();
        for(State nextState: hashMap.values()){
            return writeAllNodes(nextState, sentence.concat(writeNode(nextState)));
        }
         return sentence;
    }

    private String writeNode(State automata){
        //automata.setLabel(String.valueOf(label));
        //label++;
        if(automata.isAcceptance()){
            return "node[shape = doublecircle]" + automata.getLabel() + "[label=\"" + automata.getLabel() + "\"];\n";
        }else{
            return "node[shape = circle]" + automata.getLabel() + "[label=\"" + automata.getLabel() + "\"];\n";
        }
    }

    private String transition(State a, State b, Character character){
        return a.getLabel() + " -> " + b.getLabel() + " [label=\"" + character + "\"];\n";
    }

    public void graphDA(State automata){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/tpEspecial/grafoDA.txt"));
            bw.write("digraph{\nrankdir = \"LR\";\n");

            bw.write(writeAllNodesForDA(automata, "", new ArrayList<>()));
            bw.write(connectNodesForDA((StateImpl) automata, "", automata, new ArrayList<>()));

            bw.write("}");
            bw.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private String connectNodesForDA(StateImpl state, String sentence, State head, List<State> visited){
        sentence= sentence.concat(state.getLabel() + " -> " + head.getLabel() + " [label=\" otros \"];\n");
        visited.add(state);
        for(Map.Entry<Character, State> entry: state.getTransitions().entrySet()){
            if(!visited.contains(entry.getValue())){
                return connectNodesForDA((StateImpl) entry.getValue(), sentence.concat(transition(state, entry.getValue(), entry.getKey())), head, visited);
            }
        }
        return sentence;
    }

    private String writeAllNodesForDA(State state, String sentence, List<State> visited){
        if(visited.contains(state)) return sentence;
        HashMap<Character, State> hashMap = ((StateImpl) state).getTransitions();
        visited.add(state);
        for(State nextState: hashMap.values()){
            if(!visited.contains(nextState)) {
                return writeAllNodesForDA(nextState, sentence.concat(writeNode(nextState)), visited);
            }
        }
        return sentence;
    }
}
