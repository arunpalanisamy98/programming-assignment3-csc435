package csc435.app;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ClientSideEngine {
    private Socket socket;
    private String ip;
    private int port;
    private static boolean isConnected;
    private String clientId;

    public boolean getIsConnected(){
        return isConnected;
    }

    public ClientSideEngine() {
    }
    static Set<String> foldersAccessed = new HashSet<>();

    public void indexFiles(String rawFile, String datasetNo ) throws Exception {
        if(isConnected){
            File input = new File(rawFile);
            if (input.exists() && input.isDirectory()) {
                File[] files = input.listFiles();
                for (File file : files) {
                    if (file.isFile()) {
                        if (foldersAccessed.contains(file.getAbsolutePath())) continue;
                        indexFile(file.getAbsolutePath(), file.getName(), datasetNo);
                    }
                }
            }
        }
    }

    public void indexFile(String fileName, String name, String datasetNo) throws Exception {
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
        Data indexData = new Data();
        indexData.setFilename(datasetNo+name);
        indexData.setWordCount(wordCount);
        indexData.setConnectionType(ConnectionType.INDEX);
        socket=new Socket(ip,port);
        Util.parseAndSend(socket,indexData);
        if(!(Util.unparse(socket)).isIndexed()){
            System.out.println("Indexing failed for "+fileName);
        }
        socket.close();
    }
    
    public void searchFiles(Set<String> words) throws Exception{
        if(isConnected){
            Data searchData = new Data();
            searchData.setWords(words);
            searchData.setConnectionType(ConnectionType.SEARCH);
            socket = new Socket(ip, port);
            Util.parseAndSend(socket, searchData);
            Data response = Util.unparse(socket);
            if (!response.isResponse()) {
                System.out.println("Search operation failed");
            }
            socket.close();
            response.getResult().stream().forEach(System.out::println);
        }else{
            System.out.println("not connected to any server");
        }


    }

    public void openConnection(String ip, String port)   {
        try{
            this.ip = ip;
            this.port = Integer.parseInt(port);
            socket = new Socket(ip, Integer.parseInt(port));
            Data connectionData = new Data();
            connectionData.setRequest(true);
            connectionData.setStatus(true);
            connectionData.setConnectionType(ConnectionType.CONN);
            Util.parseAndSend(socket, connectionData);
            //System.out.println("waiting for response");
            Data data = Util.unparse(socket);
            if (data.isResponse()) {
                this.isConnected = true;
                this.clientId = data.getClientId();
                System.out.println("connected to " + ip + " at port " + port);
            } else {
                System.out.println("connection failed");
            }
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void closeConnection() throws Exception {
        if(isConnected) {
            socket = new Socket(ip, port);
            Data connectionData = new Data();
            connectionData.setRequest(true);
            connectionData.setStatus(false);
            connectionData.setClientId(clientId);
            connectionData.setConnectionType(ConnectionType.CONN);
            Util.parseAndSend(socket, connectionData);
            if ((Util.unparse(socket)).isResponse()) {
                System.out.println("disconnected from " + ip + ": " + port);
            } else {
                System.out.println("connection failed");
            }
            socket.close();
        }
        System.exit(0);
    }


}
