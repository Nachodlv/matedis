package tpEspecial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reader {

    public List<String> readFile(String filename, HashMap<String, Integer> hashMap){
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            while(line!=null){
                if(!line.equals("")) {
                    list.add(line);
                    hashMap.put(line, 0);
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void readHTML(String filename, HashMap<String, Integer> words, State automata){
        State currentState = automata;
        try {
            FileReader fileReader = new FileReader(filename);
            int read = fileReader.read();
            String word = "";
            boolean ignoring = false;
            while (read != -1){
                if(word.equals("<"))ignoring = true;
                if(word.equals(">"))ignoring = false;
                if(ignoring){
                    read = fileReader.read();
                    continue;
                }

                char character = (char) read;
                word += character;
                currentState = countChar(currentState, character);
                if(currentState.isAcceptance()){
                    Integer value = words.put(word, 1);
                    words.put(word, value + 1);
                }
                read = fileReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private State countChar(State automata, char character){
        State state = automata.transition(character);
        if(state == null) throw new IndexOutOfBoundsException();
        return state;
    }
}
