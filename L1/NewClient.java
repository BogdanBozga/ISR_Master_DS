import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
import java.net.*;
public class NewClient {

// A Java program for a Client

    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private DataInputStream server_in       =  null;
    // constructor to put ip address and port
    public NewClient(String address)
    {
        // establish a connection
        int maxPort = 81;
        for(int port=79;port<=maxPort;port++) {
            try {
                System.out.println("Trying port: " + port);
                socket = new Socket(address, port);

                socket.close();
                // takes input from terminal

            } catch (UnknownHostException u) {
                System.out.println(u);
                return;
            } catch (IOException i) {
                System.out.println(i);
            }
        }
        // string to read message from input
        String line = "";

        // keep reading until "Over" is input


    }
    public static void main(String args[])
    {
        NewClient client = new NewClient("info.uvt.ro");
    }
}