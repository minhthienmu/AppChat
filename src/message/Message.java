package message;

import java.io.Serializable;

public class Message implements Serializable {
    private String user;
    private String body;
    private int protocol;

    public Message(int protocol, String user, String body) {
        this.user = user;
        this.body = body;
        this.protocol = protocol;
    }


    public String getBody() {
        return body;
    }

    public String getUser(){
        return user;
    }

    public int getProtocol(){
        return protocol;
    }


}
