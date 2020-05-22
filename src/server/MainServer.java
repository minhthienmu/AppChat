package server;

public class MainServer{
    public static void main(String[] args) {
        int port = 8888;
        Server serverManager = new Server(port);
        serverManager.start();
    }
}

