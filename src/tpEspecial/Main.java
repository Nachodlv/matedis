package tpEspecial;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ignacio on 06/10/2017.
 */
public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader();
        List<String> list = reader.readFile("src/tpEspecial/test.txt", new HashMap<>());
        State initial = CreateAutomata.createAutomata(list);
        System.out.println("Nice");

        State deterministic = DetermineAutomaton.determine(initial);
        System.out.println("Not really");
    }
}
