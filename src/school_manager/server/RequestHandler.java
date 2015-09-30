/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author abrasha
 */
public class RequestHandler implements Runnable {

    private final Socket client;
    private final Server server;

    public RequestHandler(Socket client, Server server) {
        this.client = client;
        this.server =  server;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread started with name:" + Thread.currentThread().getName());
            readResponse();
        } catch (IOException | InterruptedException e) {

        }
    }

    private void readResponse() throws IOException, InterruptedException {
        String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String request = "";
        while ((userInput = stdIn.readLine()) != null) {
            request += userInput;
        }

        parseRequest(request);
        

    }
    
    private void parseRequest(String request){
        /* TODO
        if (request.startsWith("get"));
        */
    }

}
