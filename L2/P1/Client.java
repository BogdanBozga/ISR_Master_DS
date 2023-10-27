import java.io.*;
import java.net.*;

public class Client {

    public String send(DatagramSocket socket,String address, int serverPort, String message){
        String response = "";
        try {
            // Create a DatagramSocket
//            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(address);

//            String message = "Hello, server!";
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            System.out.println("Sent: " + message);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            // Extract the received data
            response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }
    public Client(String address, int serverPort) throws SocketException {
        DatagramSocket socket = null;

            // Create a DatagramSocket
        socket = new DatagramSocket();
           send(socket,address,serverPort,"Helllooooo");
           send(socket,address,serverPort,"time");


            if (socket != null && !socket.isClosed()) {
                socket.close();
            }

    }
}
