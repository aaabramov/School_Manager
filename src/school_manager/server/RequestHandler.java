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
import java.util.Date;

/**
 *
 * @author abrasha
 */
public class RequestHandler implements Runnable {

    private Socket client;

    public RequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread started with name:" + Thread.currentThread().getName());
            readResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void readResponse() throws IOException, InterruptedException {
        String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String request = "";
        while ((userInput = stdIn.readLine()) != null) {
            request += userInput;
        }
        
        System.out.println("Request: " + request);
        
    }

}
