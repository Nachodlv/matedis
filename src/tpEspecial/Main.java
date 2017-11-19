package tpEspecial;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File searchPath = scanDirectory("Enter the directory of the file that contains the words to be searched: ");
        File htmlFolder = scanDirectory("Enter the folder that contains the html files: ");
        File resultPath = scanDirectory("Enter the directory of the file that will have the results: ");
        File graphPath = scanDirectory("Enter the folder where the graphs will be written: ");

        final Map<String, Integer> words = Reader.readFile(searchPath.getAbsolutePath());

        final StateNDA automataNotDetermined = CreateAutomata.createAutomata(words);
        Grapher.graphNDA(automataNotDetermined, graphPath.getAbsolutePath() + "/grafoNDA.txt");

        final StateImpl automataDetermined = DetermineAutomaton.determine(automataNotDetermined);

        System.out.println("Automata created");

        Reader.writeIndexFile(htmlFolder,resultPath.getAbsolutePath() + "/index.txt", words, automataDetermined);

        Grapher.graphDA(automataDetermined, graphPath.getAbsolutePath() + "/grafoDA.txt");

        //dot -Tjpg grafoNDA.txt -o ejemplo.jpg --> transform grafoNDA.txt to a .jpg
    }


    static File scanDirectory(String sentence){
        Scanner scanner = new Scanner(System.in);
        System.out.print(sentence);
        String path = scanner.next();
        return new File(path);
    }

}
