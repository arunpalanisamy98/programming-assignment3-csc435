package csc435.app;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerSideEngine {
    private IndexStore store;


    public ServerSideEngine(IndexStore store) {
        this.store = store;
        // TO-DO implement constructor
    }

    public void initialize(int port) throws Exception{
        Dispatcher dispatcher = new Dispatcher(store,
                new ServerSocket(port));
        new Thread(dispatcher).start();
    }

    public void spawnWorker() throws Exception {


    }

    public void shutdown() {
        System.exit(0);
    }

    public void list() {
        for(String s: GlobalIndex.connectedClients){
            System.out.println(s);
        }
    }
}
