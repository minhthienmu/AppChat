package client.controller;

import client.Client;
import client.MainClient;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import message.Message;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static message.Protocols.*;

public class ChatViewController implements Initializable {
    private Client client;
    @FXML
    private JFXTextArea msg;

    public void setClient(Client client) {
        this.client = client;
    }

    public void Sendmsg(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void Logout(ActionEvent event) throws IOException, ClassNotFoundException {
        Message request = new Message(LOGOUT_REQUEST, null, null);
        client.Send_Object(request);
        if(client.Check_Logout()){
        MainClient.showLoginView();
        }
    }
}
