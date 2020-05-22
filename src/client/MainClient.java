package client;

import client.controller.ChatViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClient extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainClient.primaryStage = primaryStage;
        showLoginView();
    }

    public static void showLoginView() throws IOException {
        Parent root = FXMLLoader.load(MainClient.class.getResource("view/LoginView.fxml"));
        primaryStage.setTitle("App Chat");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void showAppView(Client client) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainClient.class.getResource("view/ChatView.fxml"));
        Parent root = loader.load();

        ChatViewController cvc = loader.getController();
        cvc.setClient(client);

        primaryStage.setTitle("Chat 419");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
