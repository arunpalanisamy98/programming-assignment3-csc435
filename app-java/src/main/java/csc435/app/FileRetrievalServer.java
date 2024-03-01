package csc435.app;

public class FileRetrievalServer {
    public static void main( String[] args ) throws Exception
    {
        int port = Integer.parseInt(args[0]);
        IndexStore store = new IndexStore();
        ServerSideEngine engine = new ServerSideEngine(store);
        ServerAppInterface appInterface = new ServerAppInterface(engine);
        
        engine.initialize(port);
        appInterface.readCommands();
    }
}
