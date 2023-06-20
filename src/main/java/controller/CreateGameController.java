package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import util.ClientConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CreateGameController implements Initializable {

    @FXML
    private Button btnCreateGame;

    @FXML
    private TextField textCreateGame;

    @FXML
    private Button btnJoinGame;

    @FXML
    private TextField codeGame;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private TextField textNamePlayer;

    private double x = 0;
    private double y = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void Close() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainStage.fxml")));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        root.setOnMousePressed((MouseEvent event) -> {

            x = event.getSceneX();
            y = event.getSceneY();

        });

        root.setOnMouseDragged((MouseEvent event) -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(.8);

        });

        root.setOnMouseReleased((MouseEvent event) -> {

            stage.setOpacity(1);

        });


        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        // Cerrar la ventana CreateRoom
        Stage   createroom = (Stage) btnClose.getScene().getWindow();
        createroom.close();

    }

    @FXML
    void Minimize(MouseEvent event) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void CreateNewPlayer() throws IOException {
        /*
        System.out.println("Ejecutando");
        ClientConnection client = ClientConnection.getInstance();
        System.out.println(textNamePlayer.getText());
        client.sendMessageToServer(textNamePlayer.getText());
         */
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/BoardGame.fxml")));
        Scene scene = new Scene(root, 1100, 700);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        // Cerrar la ventana Creatroom
        Stage room = (Stage) btnCreateGame.getScene().getWindow();
        room.close();
    }

}
