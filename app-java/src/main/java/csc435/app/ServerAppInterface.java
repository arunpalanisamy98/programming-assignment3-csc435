package csc435.app;

import java.lang.System;
import java.util.Scanner;

public class ServerAppInterface {
    private ServerSideEngine engine;

    public ServerAppInterface(ServerSideEngine engine) {
        this.engine = engine;

    }

    public void readCommands() {
        Scanner sc = new Scanner(System.in);
        String command;
        
        while (true) {
            System.out.print("> ");
            
            // read from command line
            command = sc.nextLine();

            // if the command is quit, terminate the program       
            if (command.compareTo("quit") == 0) {
                engine.shutdown();
                break;
            }

            // if the command begins with list, list all the connected clients
            if (command.length() >= 4 && command.substring(0, 4).compareTo("list") == 0) {

                engine.list();
                continue;
            }

            System.out.println("unrecognized command!");
        }

        sc.close();
    }
}
