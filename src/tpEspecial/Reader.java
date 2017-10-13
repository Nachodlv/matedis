package tpEspecial;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class    Reader {

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
            br.close();
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
                if(currentState == null) currentState = automata;
                if(currentState.isAcceptance()){
                    Integer value = words.put(word, 1);
                    words.put(word, value + 1);
                }
                read = fileReader.read();
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readHTML1(String filename, HashMap<String, Integer> words, State automata){
        int random = (int)(Math.random()*10);
        for (String word : words.keySet()) {
            System.out.println(word+": "+random);
            words.put(word,random);
            random = (int)(Math.random()*10);
        }
    }

    private State countChar(State automata, char character){
        State state = automata.transition(character);
        return state;
    }

    public void writeIndexFile(File directory, String filenameTxt){
        HashMap<String, Integer> words = new HashMap<>();
        final List<String> wordsList = readFile(filenameTxt, words);
        final State automata = CreateAutomata.createAutomata(wordsList);

        List<HashMap<String, Integer>> hashMapList = new ArrayList<>();
        final File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            HashMap<String, Integer> wordsInFile = new HashMap<>(words);//para que no cambie words
            readHTML("src/tpEspecial/"+directory.getName()+"/"+files[i].getName(),wordsInFile,automata);
            hashMapList.add(wordsInFile);
        }

        FileWriter fw;
        BufferedWriter bw;
        try {
            fw = new FileWriter("src/tpEspecial/index.txt");
            bw = new BufferedWriter(fw);
            for (String word : words.keySet()){
                bw.write(word);
                bw.newLine();
                for (int i = 0; i < hashMapList.size(); i++) {
                    HashMap<String, Integer> hashMap = hashMapList.get(i);
                    final int repetitions = hashMap.get(word);
                    if (repetitions!=0){
                        bw.write(files[i].getName());
                        bw.newLine();
                        bw.write(String.valueOf(repetitions));
                        bw.newLine();
                    }
                }
                bw.write("");
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
