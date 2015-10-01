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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import school_manager.server.Request.Category;

/**
 *
 * @author abrasha
 */
public class RequestHandler implements Runnable {

    // regex of requst
    // CATEGORY:METHOD?(METHOD_SPECIFIC_PARAMS)
    public static void main(String[] args) {

        
        
    }

    private final Socket client;
    private final Server server;
    private Request request = null;
    private String URL = null;
    public static final String INVALID_REQUEST = "error:invalid_request";
    public static final String CLIENT_NULL = "error:client_null";
    public static final String SERVER_NULL = "error:server_null";

    public RequestHandler(Socket client, Server server) throws IOException {
        
        if (client == null){
            sendResponse(CLIENT_NULL);
            throw new NullPointerException();
        }
        if (server == null){
            sendResponse(SERVER_NULL);
            throw new NullPointerException();
        }
        
        this.client = client;
        this.server = server;
        
        if ( (URL = getRequest()) != null){
            
            request = new Request(URL);
            
        } else {
            
            sendResponse(INVALID_REQUEST);
            
        }
        
        
    }

    private void sendResponse(String response) throws IOException {
        
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        writer.write(response);
        writer.flush();
        writer.close();
        
    }
    
    private boolean isValid(String request) {

        Pattern p = Pattern.compile("(user|student|parent|admin|teacher|group|schedule):"
                + "(get|update|remove|add)\\?.+");

        Matcher m = p.matcher(request);

        return m.matches();

    }
    
    private String getRequest() throws IOException {
        
        String result = "";
        String line;
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
        while ((line = reader.readLine()) != null){
            result += line;
        }
        
        return (isValid(result) ? result : null);
        
    }

    @Override
    public void run() {

    }

}
