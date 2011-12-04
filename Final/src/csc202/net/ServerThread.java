package csc202.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * User: Souleiman Ayoub
 * Date: 12/3/11
 * Time: 8:07 PM
 *
 * This class is designed to process protocol, and threading that will handle multiple clients.
 */
public class ServerThread extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ServerThread(Socket socket){
        System.out.println(socket.getInetAddress().getHostName() + " has connected.");
        this.socket = socket;
    }

    /**
     * Handles the input and output
     */
    public void run(){
        try{
            out = new PrintWriter(socket.getOutputStream(), true); //Writer that will write to client
            in = new BufferedReader( //Reads transaction from clients.
                    new InputStreamReader(
                    socket.getInputStream()));

            String input;
            boolean output;

            input = in.readLine(); //Read what the client sent.
            output = Protocol.process(input); //Process the protocol
            out.println(output); //Return protocol result
            exit(); //Closes the socket and stream
        } catch (SocketException e){ //When client breaks away during process
            interrupt();
            exit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the streams.
     */
    private void exit(){
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
