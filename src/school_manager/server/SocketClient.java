package school_manager.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author abrasha
 *
 */
public class SocketClient {

    public static void main(String arg[]) {

        SocketClient client = new SocketClient("localhost", SocketServer.DEFAULT_PORT);
        try {
            client.connect();
            client.sendRequest("user:get?id=5");
            String response = client.readResponse();
            System.out.println("Response: " + response);

        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection: " + e);
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up: " + e.getMessage());
        }
    }

    private final String hostname;
    private final int port;
    Socket socketClient;

    public SocketClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException {
        System.out.println("Connecting to " + hostname + ":" + port);
        socketClient = new Socket(hostname, port);
        System.out.println("Connection Established");
    }

    public String readResponse() throws IOException {
        String response = "", line;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        while ( (line = stdIn.readLine() ) != null) {
            response += line;
        }

        return response;
    }

    private void sendRequest(String request) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        writer.write(request);
        writer.newLine();
        writer.flush();
    }
    
    
}
