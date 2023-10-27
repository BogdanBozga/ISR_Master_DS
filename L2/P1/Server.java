import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Calendar;
public class Server {
    DatagramSocket socket = null;
    public Server(int port)
    {


        try {
            // Create a DatagramSocket and bind it to port 5000
            socket = new DatagramSocket(port);
            System.out.print("Server started on port: ");
            System.out.println(port);
            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                // Extract the received data and client's address
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received from " + clientAddress + ":" + clientPort + " - " + message);

                // Send a response back to the client
                String response = message;
                if(message.equals("time")){
                    Calendar calendar = Calendar.getInstance();
                    Date date = calendar.getTime();
                    response = String.valueOf(date);
                }

                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
