package school_manager.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author abrasha
 */
public class RequestHandler implements Runnable {

    private final Socket client;
    private Request request = null;
    private String URL = null;
    public static final String INVALID_REQUEST = "error:invalid_request";
    public static final String CLIENT_NULL = "error:client_null";

    public RequestHandler(Socket client) {

        this.client = client;

    }

    @Override
    public void run() {

        URL = readRequest();

        if (isValid(URL)) {

            request = new Request(URL);
            System.out.println("Parsed request:\n" + request);
            sendResponse(request.toString());

        } else {

            sendResponse(INVALID_REQUEST);

        }

    }

    private String readRequest() {

        String result = null;
        BufferedReader stdIn;
        try {
            stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
            result = stdIn.readLine();
        } catch (IOException e) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, e);
        }

        return result;

    }

    private void sendResponse(String response) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            writer.write(response);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private boolean isValid(String request) {

        if (request == null) {
            return false;
        }

        Pattern p = Pattern.compile("(user|student|parent|admin|teacher|group|schedule):"
                + "(get|update|remove|add)\\?.+");

        Matcher m = p.matcher(request);

        return m.matches();

    }

}
