package tpEspecial;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new Reader().readFile("src/tpEspecial/words.txt", new HashMap<>());
        State state = CreateAutomata.createAutomata(list);
        System.out.println();
    }
}
