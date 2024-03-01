package csc435.app;


import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher implements Runnable {
    private IndexStore indexStore;
    private ServerSocket serverSocket;
    private Socket socket;

    public Dispatcher(IndexStore indexStore, ServerSocket serverSocket) throws Exception {
        this.indexStore = indexStore;
        this.serverSocket=serverSocket;
        // TO-DO implement constructor
    }
    
    @Override
    public void run() {
        try{
            while(true){
                socket = serverSocket.accept();
                Worker worker = new Worker(indexStore,socket);
                new Thread(worker).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
