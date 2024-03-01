package csc435.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

public class Worker implements Runnable {
    private IndexStore store;
    private Socket socket;

    public Worker(IndexStore store, Socket socket) {
        this.store = store;
        this. socket = socket;
    }
    
    @Override
    public void run() {
        try {
            handleRequests();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleRequests() throws Exception{
        Data req = (Data) new ObjectInputStream(socket.getInputStream()).readObject();
        ConnectionType data = req.getConnectionType();
       // System.out.println("post parsing");
        if(data.equals(ConnectionType.CONN)){
                if(req.isStatus()){
                    GlobalIndex.connectedClients.add(socket.getInetAddress().getHostName()+" "+socket.getPort());
                    req.setClientId(""+socket.getPort());
                }else{
                    GlobalIndex.connectedClients.remove(socket.getInetAddress().getHostName()+" "+req.getClientId());
                }
                req.setResponse(true);
                //System.out.println("sending to client");
                Util.parseAndSend(socket,req);
                socket.close();
            } else  if(data.equals(ConnectionType.INDEX)) {
                GlobalIndex.globalIndex.put(req.getFilename(),req.getWordCount());
                req.setIndexed(true);
                Util.parseAndSend(socket,req);
                socket.close();
            }
            else  if(data.equals(ConnectionType.SEARCH)) {
                Set<String> words =req.getWords();
                if(words.size()==1){
                    String word=null;
                    for(String s:words) {
                        word = s;
                    }
                    Set<String> ans =store.lookupIndex(word);
                    req.setResponse(true);
                    req.setResult(ans);
                    Util.parseAndSend(socket,req);
                }else{
                    String str[]=new String[2]; int i=0;
                    for(String s:words){
                        str[i]=s;
                        i++;
                    }
                    req.setResponse(true);
                    Set<String> ans =store.lookupIndex(str[0],str[1]);
                    req.setResult(ans);
                    Util.parseAndSend(socket,req);
                }
                socket.close();
            }
            else {
                System.out.println("unrecogonized request");
            }
        }
}
