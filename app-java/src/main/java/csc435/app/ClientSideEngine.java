package csc435.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ClientSideEngine {
    /*private IndexStore store;
    private String rawFile;
    private String datasetNo;*/
    private Socket socket;

    public ClientSideEngine() {
        
        // TO-DO implement constructor
    }

    public ClientSideEngine(ServerSocket ss) throws Exception  {
        socket = ss.accept();
    }
    static Set<String> foldersAccessed = new HashSet<>();

    public void indexFiles(String rawFile, String datasetNo  ) throws Exception {
        File input = new File(rawFile);
        if (input.exists() && input.isDirectory()) {
            File[] files = input.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    if(foldersAccessed.contains(file.getAbsolutePath())) continue;
                    cleanFiles(file.getAbsolutePath(), file.getName(),datasetNo );
                }
            }
        }
    }

    public void cleanFiles(String fileName, String name, String datasetNo) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        foldersAccessed.add(datasetNo+name);
        String line;
        HashMap<String, Integer> wordCount = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            line = line.replace("\r", "");
            line = line.replace("\\s+", "");
            line = line.replace("[^\\w\\s", "");
            line = line.replaceAll("[^a-zA-Z0-9 ]", "");
            String[] str = line.split(" ");
            for (String s : str) {
                if(s.equals("")) continue;
                if(wordCount.containsKey(s)){
                    wordCount.put(s, wordCount.get(s) + 1);
                }
                else{
                    wordCount.put(s, 1);
                }
            }
        }
        //store.insertIndex(datasetNo+name,wordCount);
    }
    
    public void searchFiles() {
        // TO-DO implement search files method
        // for each term contact the server to retrieve the list of documents that contain the word
        // combine the results of a multi-term query
        // return top 10 results
    }

    public void openConnection()  {

    }

    public void closeConnection() {

    }
}
