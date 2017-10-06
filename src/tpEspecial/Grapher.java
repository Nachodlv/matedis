package tpEspecial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Grapher {
    public void graphNDA(State automata){
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter("grafoNDA"));
            bw.write("digraph{\nrankdir = \"LR\";\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNode(State automata){

    }
}
