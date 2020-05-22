package server;


import message.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static message.Protocols.*;


public class ServerWorker extends Thread{
    private final Server server;
    private final Socket clientSocket;
    private String user = null;
    private InputStream inputStream;
    private OutputStream outputStream;


    private ObjectInputStream object_input_stream;
    private ObjectOutputStream object_output_stream;

    public ServerWorker(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClient();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleClient() throws IOException, ClassNotFoundException {
        inputStream = clientSocket.getInputStream(); //Luong nhan du lieu tu Client gui den
        this.outputStream = clientSocket.getOutputStream(); //Luong tra du lieu cho Client
        this.object_output_stream = new ObjectOutputStream(outputStream);
        this.object_input_stream = new ObjectInputStream(inputStream);

        while(true) {
            Message request = (Message) object_input_stream.readObject();
            int protocol = request.getProtocol();
            if (protocol == LOGIN_REQUEST) {
                System.out.println(request.getProtocol() + " " + request.getUser());
                handleLogin(request);
            } else if (protocol == SEND_MESSAGE) {
                System.out.println(request.getProtocol() + "from: " + request.getUser());
                handleMessage(request);
            } else if (protocol == LOGOUT_REQUEST) {
                System.out.println(request.getProtocol() + " " + this.user);
                handleLogout();
            } else {
                System.out.println("No request");
            }
        }
    }

    private void handleLogout() throws IOException {
        Message reply = new Message(LOGOUT_SUCCESS,this.user,null);
        Send_Object(reply);
        server.removeWorker(this);
        //clientSocket.close();
    }

    private void handleMessage(Message request) throws IOException {
        String receiver = request.getUser();
        String msg =  request.getBody();

        ArrayList<ServerWorker> workerList = server.getWorkerList();
        for(ServerWorker worker: workerList) {
            if (receiver.equalsIgnoreCase(worker.getLogin())) {
                Message reply = new Message(SEND_MESSAGE, receiver, msg);
                Send_Object(reply);
            }
        }
    }

    private void handleLogin(Message request) throws IOException {
        String username = request.getUser();
        String password = request.getBody();

        if (username.equals("thien") && password.equals("thien")){
            Message reply = new Message(LOGIN_SUCCESS, username, "login success");
            Send_Object(reply);
            this.user = username;
        } else {
            Message reply = new Message(LOGIN_FAILED, username, null);
            Send_Object(reply);
        }
    }

    public void Send_Object(Message obj) throws IOException {
        object_output_stream.writeObject(obj);
    }

    private String getLogin() {
        return user;
    }


}
