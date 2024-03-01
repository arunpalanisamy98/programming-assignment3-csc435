package csc435.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Util {

    static void parseAndSend(Socket justasocket, Data data) throws Exception{
        getObjectOutputStream(justasocket).writeObject(data);
    }

    static ObjectInputStream getObjectInputStream(Socket s) throws Exception{
        return new ObjectInputStream(s.getInputStream());
    }

    static Data unparse(Socket s) throws Exception {
        return (Data) getObjectInputStream(s).readObject();
    }


    static ObjectOutputStream getObjectOutputStream(Socket s) throws Exception{
        return new ObjectOutputStream(s.getOutputStream());
    }
}
