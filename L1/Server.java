// A Java program for a Server
import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Calendar;

public class Server
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;

    private DataOutputStream out = null;
    // constructor with port
    public Server(int port)
    {
        Calendar calendar = Calendar.getInstance();

        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(
                    socket.getOutputStream());

            String line = "";

            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
//                    line = in.readUTF();
                    Thread.sleep(1000);
                    calendar = Calendar.getInstance();
                    Date date = calendar.getTime();
                    out.writeUTF(String.valueOf(date));

                }
                catch(IOException i)
                {
                    System.out.println(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}