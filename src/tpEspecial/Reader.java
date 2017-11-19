package tpEspecial;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader {

    public static Map<String, Integer> readFile(String filename){
        Map<String, Integer> hashMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            while(line!=null){
                if(!line.equals("")) {
                    hashMap.put(line, 0);
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public static void readHTML(String filename, HashMap<String, Integer> words, StateImpl automata){
        StateImpl currentState = automata;
        try {
            FileReader fileReader = new FileReader(filename);
            int read = fileReader.read();
            boolean ignoring = false;
            while (read != -1){
                if(read == '<')ignoring = true;
                if(read == '>'){
                    ignoring = false;
                    read = fileReader.read();
                    continue;
                }
                if(ignoring){
                    read = fileReader.read();
                    continue;
                }

                char character = (char) read;
                currentState = (StateImpl) currentState.transition(character);
                if(currentState == null){
                    currentState = automata;
                }
                if(currentState.isAcceptance()){
                    for(String word:currentState.getAcceptanceWords()){
                        Integer value = words.get(word);
                        words.put(word, value + 1);
                    }
                }
                read = fileReader.read();
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeIndexFile(File directory, String indexPath, Map<String, Integer> words, StateImpl automataDetermined){

        List<HashMap<String, Integer>> hashMapList = new ArrayList<>();
        final File[] files = directory.listFiles();

        if(files != null)
            for (File file : files) {
                HashMap<String, Integer> wordsInFile = new HashMap<>(words);
                readHTML(file.getAbsolutePath(), wordsInFile, automataDetermined);
                hashMapList.add(wordsInFile);
            }

        FileWriter fw;
        BufferedWriter bw;
        try {
            fw = new FileWriter(indexPath);
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
