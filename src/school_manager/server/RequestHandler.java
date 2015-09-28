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
        while ((userInput = stdIn.readLine()) != null) {
            if (userInput.equals("TIME?")) {
                System.out.println("REQUEST TO SEND TIME RECEIVED. SENDING CURRENT TIME");
                sendTime();
                break;
            }
            System.out.println(userInput);
        }
    }

    private void sendTime() throws IOException, InterruptedException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        writer.write(new Date().toString());
        writer.flush();
        writer.close();
    }

}
