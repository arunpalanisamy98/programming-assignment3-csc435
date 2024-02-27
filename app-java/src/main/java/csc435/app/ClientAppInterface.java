package csc435.app;

import java.lang.System;
import java.util.Scanner;

public class ClientAppInterface {
    private ClientSideEngine engine;

    public ClientAppInterface(ClientSideEngine engine) {
        this.engine = engine;

        // TO-DO implement constructor
        // keep track of the connection with the client
    }

    public void readCommands() throws Exception{
        // TO-DO implement the read commands method
        Scanner sc = new Scanner(System.in);
        String command;
        
        while (true) {
            System.out.print("> ");
            
            // read from command line
            command = sc.nextLine();

            // if the command is quit, terminate the program       
            if (command.compareTo("quit") == 0) {
                engine.closeConnection();
                break;
            }

            // if the command begins with connect, connect to the given server
            if (command.length() >= 7 && command.substring(0, 7).compareTo("connect") == 0) {
                engine.openConnection();
                continue;
            }
            
            // if the command begins with index, index the files from the specified directory
            if (command.length() >= 5 && command.substring(0, 5).compareTo("index") == 0) {
                String[] arr = command.split(" ");
                if(arr.length != 2) {
                    System.out.println("Invalid command");
                    continue;
                }
                String path = arr[1].trim();
                String datasetNo = path.substring(path.length() - 1);
                long startTime = System.currentTimeMillis();
                engine.indexFiles(path,datasetNo);
                long endTime = System.currentTimeMillis();
                System.out.println("Indexing took " + (endTime - startTime)/1000 + " seconds");
                continue;
            }

            // if the command begins with search, search for files that matches the query
            if (command.length() >= 6 && command.substring(0, 6).compareTo("search") == 0) {
                // TO-DO implement index operation
                // extract the terms and call the server side engine method to search the terms for files
                continue;
            }

            System.out.println("unrecognized command!");
        }

        sc.close();
    }
}
