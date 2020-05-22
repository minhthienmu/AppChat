package client.controller;

import client.Client;
import client.MainClient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import message.Message;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static message.Protocols.*;

public class LoginController implements Initializable {
    Client client = new Client("localhost", 8888);
    //public Message reply = new Message(0, null,null);

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton btnlogin;


    @FXML
    void Login(ActionEvent event) throws IOException, ClassNotFoundException {
        String user = username.getText();
        String pass = password.getText();
        Message request = new Message(LOGIN_REQUEST, user, pass);
        client.Send_Object(request);
        if (client.Check_Login()) {
            MainClient.showAppView(client);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client.connect();
    }


}
