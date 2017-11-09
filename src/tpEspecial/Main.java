package tpEspecial;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final String SEARCH_TEXT = ("src/tpEspecial/search.txt");

        final Map<String, Integer> words = Reader.readFile(SEARCH_TEXT);

        final StateNDA automataNotDetermined = CreateAutomata.createAutomata(words);
        Grapher.graphNDA(automataNotDetermined);

        final StateImpl automataDetermined = DetermineAutomaton.determine(automataNotDetermined);

        System.out.println("Automata created");

        File directory = new File("src/tpEspecial/htmlDirectory");
        Reader.writeIndexFile(directory,"src/tpEspecial/search.txt", words, automataDetermined);

        Grapher.graphDA(automataDetermined);

        //dot -Tjpg grafoNDA.txt -o ejemplo.jpg --> transform grafoNDA.txt to a .jpg
        
    }

}
