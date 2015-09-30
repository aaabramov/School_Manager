/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.server;

import java.net.Socket;
import school_manager.server.Request.Category;

/**
 *
 * @author abrasha
 */
public class RequestHandler implements Runnable {

    
    // regex of requst
    // CATEGORY:METHOD?(METHOD_SPECIFIC_PARAMS)
    
    private final Socket client;
    private final Server server;

    public RequestHandler(Socket client, Server server) {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        
    }

    

}
