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

    public static void graphNDA(State automata){
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

    private static String connectNodes(State stateA, State stateB, char connect, String sentence){
        for(Map.Entry<Character,State> entry: ((StateImpl)stateB).getTransitions().entrySet()){
            return connectNodes(stateB,entry.getValue(), entry.getKey(), sentence.concat(transition(stateA, stateB, connect)));
        }
        return sentence.concat(transition(stateA, stateB, connect));
    }

    private static String writeAllNodes(State state, String sentence){
        HashMap<Character, State> hashMap = ((StateImpl) state).getTransitions();
        for(State nextState: hashMap.values()){
            return writeAllNodes(nextState, sentence.concat(writeNode(nextState)));
        }
         return sentence;
    }

    private static String writeNode(State automata){
        //automata.setLabel(String.valueOf(label));
        //label++;
        if(automata.isAcceptance()){
            return "node[shape = doublecircle]" + automata.getLabel() + "[label=\"" + automata.getLabel() + "\"];\n";
        }else{
            return "node[shape = circle]" + automata.getLabel() + "[label=\"" + automata.getLabel() + "\"];\n";
        }
    }

    private static String transition(State a, State b, Character character){
        return a.getLabel() + " -> " + b.getLabel() + " [label=\"" + character + "\"];\n";
    }

    public static void graphDA(State automata){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/tpEspecial/grafoDA.txt"));
            bw.write("digraph{\nrankdir = \"LR\";\n");

            List<String> transitions = new ArrayList<>();
            writeAllNodesForDA(automata, bw, new ArrayList<>(), transitions, automata);
            addAllTransitions(transitions, bw);
            //bw.write(nodes);
            //bw.write(connectNodesForDA((StateImpl) automata, "", automata, new ArrayList<>()));


            bw.write("}");
            bw.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void writeAllNodesForDA(State state, BufferedWriter writer, List<State> visited, List<String> transitions, State head) throws IOException{
        if(visited.contains(state)) return;
        HashMap<Character, State> hashMap = ((StateImpl) state).getTransitions();
        visited.add(state);
        addAllTransitions(state, hashMap, transitions, head);
        writer.write(writeNode(state));
        for(State nextState: hashMap.values()){
            writeAllNodesForDA(nextState, writer, visited, transitions, head);
        }
    }

    private static void addAllTransitions(State state, HashMap<Character, State> hashMap, List<String> transitions, State head){
        transitions.add(state.getLabel() + " -> " + head.getLabel() + " [label=\" otros \"];\n");
        for(Map.Entry<Character, State> entry: hashMap.entrySet()){
            transitions.add(transition(state, entry.getValue(), entry.getKey()));
        }
    }

    private static void addAllTransitions(List<String> transitions, BufferedWriter writer) throws IOException{
        for(String transition : transitions){
            writer.write(transition);
        }
    }
}
