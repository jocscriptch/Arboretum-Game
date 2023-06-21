package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainStageController implements Initializable {

    @FXML
    private AnchorPane mainStage;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRules;

    @FXML
    private Button btnAbout;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private AnchorPane rulesStage;

    @FXML
    private Button btnMinimize1;

    @FXML
    private Button btnClose1;

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane aboutStage;

    @FXML
    private Button btnWhats;

    @FXML
    private Button btnWhats1;

    @FXML
    private Button btnWhats2;

    @FXML
    private Button btnClose2;

    @FXML
    private Button btnMinimize2;

    @FXML
    private Button btnBack1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void Play(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CreateRoom.fxml")));
        Scene scene = new Scene(root, 1100, 700);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

        // Cerrar la ventana MainStage
        Stage mainStage = (Stage) btnPlay.getScene().getWindow();
        mainStage.close();


    }

    @FXML
    public void Close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    public void Minimize(MouseEvent event) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void changeStage(ActionEvent event) {

        if (event.getSource() == btnRules) {

            errorNotification("¡Hola xD!");
            mainStage.setVisible(false);
            aboutStage.setVisible(false);
            rulesStage.setVisible(true);

        } else if (event.getSource() == btnAbout) {

            mainStage.setVisible(false);
            rulesStage.setVisible(false);
            aboutStage.setVisible(true);
        }
    }

    @FXML
    public void backStage(ActionEvent event) {

        if (event.getSource() == btnBack) {

            rulesStage.setVisible(false);
            aboutStage.setVisible(false);
            mainStage.setVisible(true);

        } else if (event.getSource() == btnBack1) {

            rulesStage.setVisible(false);
            aboutStage.setVisible(false);
            mainStage.setVisible(true);

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

    @FXML
    public void link(){
        btnWhats.setOnAction(event -> {
            String url = "";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }
}

