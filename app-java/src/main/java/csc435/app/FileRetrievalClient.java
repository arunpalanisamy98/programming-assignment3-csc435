package csc435.app;

import java.net.ServerSocket;

public class FileRetrievalClient
{
    public static void main(String[] args) throws Exception
    {
        ClientSideEngine engine = new ClientSideEngine(new ServerSocket());
        ClientAppInterface appInterface = new ClientAppInterface(engine);
        
        // read commands from the user
        appInterface.readCommands();
    }
}
