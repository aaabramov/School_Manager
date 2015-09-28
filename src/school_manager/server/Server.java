/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author abrasha
 */
public class Server {
    
    private ServerSocket serverSocket;
    private int port;
    
    public Server(int port) {
        this.port = port;
    }
    
    public void start() throws IOException {
        System.out.println("Starting the socket server at port:" + port);
        serverSocket = new ServerSocket(9991);

        Socket client = null;
        
        while(true){
            System.out.println("Waiting for clients...");
            client = serverSocket.accept();
            System.out.println("The following client has connected:");
            //A client has connected to this server. Send welcome message
            Thread thread = new Thread(new RequestHandler(client));
            thread.start();
        }     
    }
    
    
    
    /**
    * Creates a SocketServer object and starts the server.
    *
    * @param args
    */
    public static void main(String[] args) {
        // Setting a default port number.
        int portNumber = 9991;
        
        try {
            // initializing the Socket Server
            Server socketServer = new Server(portNumber);
            socketServer.start();
            
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
