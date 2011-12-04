package csc202.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * User: Souleiman Ayoub
 * Date: 12/3/11
 * Time: 6:24 PM
 */
public class Client {
    private Socket socket;

    /**
     * Connect the client with the server.
     *
     * @param host location of where the server is.
     * @param port port where the server is listening.
     *
     * @throws IOException in case the server isn't listening or not open.
     */
    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }

    /**
     * Processes the Transaction to the server. Server should return a boolean.
     *
     * @param transaction that the server would process
     * @return true if the process is accepted, otherwise false.
     */
    public boolean processTransaction(String transaction){
        PrintWriter out;
        Scanner in;

        if(socket.isConnected()){
            System.out.println("Connected.");
        } else {
            return false;
        }

        try{
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            return false;
        }

        out.println(transaction); //Write the transaction to the server
        boolean accept = in.nextBoolean(); //Waits for server to respond.

        out.close();
        in.close();
        return accept;
    }

    /**
     * Closes the connection between the server.
     *
     * @throws IOException in case something goes wrong.
     */
    public void close() throws IOException {
        socket.close();
    }
}
