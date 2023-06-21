package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import util.ClientConnection;

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

    //ClientConnection client = ClientConnection.getInstance();



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
        try {
            String namePlayer = textNamePlayer.getText();
            PlayerSinglenton.getInstance().setName(namePlayer);
            PlayerSinglenton playerName = PlayerSinglenton.getInstance();
            playerName.setName(namePlayer);
            System.out.println("Ejecutando");
            System.out.println(textNamePlayer.getText());
            //ClientConnection client = ClientConnection.getInstance();
            //client.sendMessageToServer(textNamePlayer.getText()+":Nothing");
            //System.out.println(client.responseMessageToServer());
            // codigo server
            /*try {
                System.out.println(client.responseMessageToServer());
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Error no recibe nada");
            }*/


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BoardGame1.fxml"));
            Parent root = loader.load();
            BoardGameController boardController = loader.getController();

            Scene scene = new Scene(root, 1100, 650);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Cerrar la ventana CreateRoom
            Stage createroom = (Stage) btnCreateGame.getScene().getWindow();
            createroom.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void successNotification(String message) {
        String musicFile = "notify.mp3";
        AudioClip mApplause = new AudioClip(Objects.requireNonNull(this.getClass().getResource("/sounds/notify.mp3")).toExternalForm());
        mApplause.play();
        javafx.scene.image.Image img = new javafx.scene.image.Image("/images/check.png");
        Notifications notification = Notifications.create();
        notification.graphic(new ImageView(img));
        notification.title("Notificación");
        notification.text(message);
        notification.hideAfter(Duration.seconds(5));
        notification.position(Pos.TOP_RIGHT);
        notification.show();
    }

    public void errorNotification(String message) {

        String musicFile = "error.mp3";
        AudioClip mApplause = new AudioClip(Objects.requireNonNull(this.getClass().getResource("/sounds/error.mp3")).toExternalForm());
        mApplause.play();
        javafx.scene.image.Image img = new Image("/images/error.png");
        Notifications notification = Notifications.create();
        notification.graphic(new ImageView(img));
        notification.title("Notificación");
        notification.text(message);
        notification.hideAfter(Duration.seconds(5));
        notification.position(Pos.TOP_CENTER);
        notification.show();

    }

}

