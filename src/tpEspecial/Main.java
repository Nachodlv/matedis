package tpEspecial;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader();
        List<String> list = reader.readFile("src/tpEspecial/words.txt", new HashMap<>());
        State initial = CreateAutomata.createAutomata(list);
        System.out.println("Nice");

        /*State deterministic = DetermineAutomaton.determine(initial);
        System.out.println("Not really");
        */

        //dot -Tjpg grafoNDA.txt -o ejemplo.jpg --> transform grafoNDA.txt to a .jpg
        new Grapher().graphNDA(initial);

    }
}
