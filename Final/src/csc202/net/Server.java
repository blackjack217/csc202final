package csc202.net;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * User: Souleiman Ayoub
 * Date: 12/3/11
 * Time: 7:22 PM
 */
public class Server extends Thread{
    private ServerSocket serverSocket;

    /**
     * Open a socket that will listen to other sockets.
     *
     * @param port where the socket will be listening.
     * @throws IOException in case port is taken
     */
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run(){ //Handles multiple recipients.
        while(!serverSocket.isClosed()){
            try{
                new ServerThread(serverSocket.accept()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
