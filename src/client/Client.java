package client;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client{
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private ObjectInputStream object_input_stream;
    private ObjectOutputStream object_output_stream;

    public Client(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public void connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            System.out.println("Client port is " + socket.getLocalPort());
            object_output_stream = new ObjectOutputStream(socket.getOutputStream()); //luong gui du lieu toi server
            object_input_stream = new ObjectInputStream(socket.getInputStream()); // luong nhan du lieu tu server

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Send_Object(Message obj) throws IOException {
        object_output_stream.writeObject(obj);
        System.out.println(obj.getProtocol());
    }

    public void Receive_Object() throws IOException, ClassNotFoundException {
        Message reply = (Message)object_input_stream.readObject();
        System.out.println(reply.getProtocol());
        System.out.println(reply.getBody());
    }



}
