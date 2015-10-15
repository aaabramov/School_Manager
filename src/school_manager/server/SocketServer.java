package school_manager.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author abrasha
 */
public class SocketServer {
    
    public static final int DEFAULT_PORT = 10001;
    
    private ServerSocket socket;
    private final int port;
    private int clientsServed;
    
    private static final Logger logger;
    
    static {
        logger = Logger.getLogger(SocketServer.class.getClass().getCanonicalName());
    }
    
    public SocketServer(){
        this.port = DEFAULT_PORT;
        this.clientsServed = 0;
    }
    
    public SocketServer(int port) {
        this.port = port;
        this.clientsServed = 0;
    }
    
    public void start() {
        try {
            System.out.println("Starting the server at port: " + port);
            
            socket = new ServerSocket(port);
            
            Socket client;
            
            while (true) {
                System.out.println("Waiting for clients...");
                client = socket.accept();
                logger.log(Level.INFO, "Client #{0} connected.", (++clientsServed));
                Thread thread = new Thread(new RequestHandler(client));
                thread.start();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server.start: ", e);
        }
    }
    
    public static void main(String[] args) {
        
        //int portNumber = 9991;
        
        SocketServer socketServer = new SocketServer();
        socketServer.start();
    }
    
    public void log(Level level, String message) {
        logger.log(level, message);
    }
    
}
