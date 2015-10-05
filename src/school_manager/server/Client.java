/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author abrasha
 */
public class Client {
    
    private String hostname;
    private int port;
    Socket socketClient;

    public Client(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException{
        System.out.println("Attempting to connect to "+hostname+":"+port);
        socketClient = new Socket(hostname,port);
        System.out.println("Connection Established");
    }

    public void readResponse() throws IOException{
       String userInput;
       BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

       System.out.print("RESPONSE FROM SERVER:");
       while ((userInput = stdIn.readLine()) != null) {
           System.out.println(userInput);
       }
    }
    
    public void askForTime() throws IOException{
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
       writer.write("get_student");
       writer.newLine();
       writer.flush();
    }

    public static void main(String arg[]){
        //Creating a SocketClient object
        Client client = new Client ("localhost",9991);
        try {
            //trying to establish connection to the server
            client.connect();
            client.askForTime();
            client.readResponse();
            
        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }
    }
    
}
