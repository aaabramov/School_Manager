package school_manager.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 *
 * @author abrasha
 *
 */
public class SocketServer {

    private ServerSocket serverSocket;
    private final int port;
    private int clientsServed;

    public SocketServer(int port) {
        this.port = port;
        this.clientsServed = 0;
    }

    public void start() {
        try {
            System.out.println("Starting the server at port:" + port);

            serverSocket = new ServerSocket(port);

            Socket client;

            while (true) {
                System.out.println("Waiting for clients...");
                client = serverSocket.accept();
                Logger.getLogger(SocketServer.class.getName()).info("Client №" + (++clientsServed) + " connected");
                Thread thread = new Thread(new RequestHandler(client));
                thread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        int portNumber = 9991;

        SocketServer socketServer = new SocketServer(portNumber);
        socketServer.start();
    }
}
