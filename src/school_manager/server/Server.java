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
    private int port = 9991;
    private boolean running = true;
    
    public void start() throws IOException {
        System.out.println("Starting the socket server at port:" + port);
        serverSocket = new ServerSocket(port);

        Socket client = null;
        
        while(running){
            System.out.println("Waiting for clients...");
            client = serverSocket.accept();
            Thread thread = new Thread(new RequestHandler(client, this));
            thread.start();
        }     
    }
    
    public void setRunnig(boolean running){
        this.running = running;
    }
    
    public static void main(String[] args) throws IOException {
        new Server().start();
    }
    
}
