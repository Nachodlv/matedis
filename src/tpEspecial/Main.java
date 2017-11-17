package tpEspecial;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*
        We need to ask for 5 directories.
        (next to its usage, "needs to be parametrized" is written as a comment)

        1. File that contains the words to be searched.
        default = "src/tpEspecial/textFiles/search.txt"

        2. Folder that contains the html files where the search will be conducted.
        default = "src/tpEspecial/htmlDirectory"

        3. File that will have the results of the search.
        default = "src/tpEspecial/textFiles/index.txt"

        4. File where the NDA graph will be written.
        default = "src/tpEspecial/textFiles/grafoNDA.txt"

        5. File where the DA graph will be written.
        default = "src/tpEspecial/textFiles/grafoDA.txt"
         */

        final String SEARCH_TEXT = ("src/tpEspecial/textFiles/search.txt"); //needs to be parametrized

        final Map<String, Integer> words = Reader.readFile(SEARCH_TEXT);

        final StateNDA automataNotDetermined = CreateAutomata.createAutomata(words);
        Grapher.graphNDA(automataNotDetermined);

        final StateImpl automataDetermined = DetermineAutomaton.determine(automataNotDetermined);

        System.out.println("Automata created");

        File directory = new File("src/tpEspecial/htmlDirectory"); //needs to be parametrized
        Reader.writeIndexFile(directory,"src/tpEspecial/textFiles/index.txt", words, automataDetermined); //needs to be parametrized

        Grapher.graphDA(automataDetermined);

        //dot -Tjpg grafoNDA.txt -o ejemplo.jpg --> transform grafoNDA.txt to a .jpg
        
    }

}
