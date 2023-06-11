package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLOutput;
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

    @FXML
    public void link(){
        btnWhats.setOnAction(event -> {
            String url = "https://api.whatsapp.com/send/?phone=50683626991&text&type=phone_number&app_absent=0";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }
}

