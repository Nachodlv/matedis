package tpEspecial;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader();
        List<String> list = reader.readFile("src/tpEspecial/words.txt", new HashMap<>());
        StateNDA initial = CreateAutomata.createAutomata(list);
        System.out.println("Nice");

        /*State deterministic = DetermineAutomaton.determine(initial);
        System.out.println("Not really");
        */

        //dot -Tjpg grafoNDA.txt -o ejemplo.jpg --> transform grafoNDA.txt to a .jpg
        new Grapher().graphNDA(initial);
        State determinado = DetermineAutomaton.determine(initial);
        new Grapher().graphDA(determinado);

        testWriteIndexFile();

    }

    public static void testWriteIndexFile(){
        Reader reader = new Reader();
        File directory = new File("src/tpEspecial/htmlDirectory");
        reader.writeIndexFile(directory,"src/tpEspecial/search.txt");
    }
}
