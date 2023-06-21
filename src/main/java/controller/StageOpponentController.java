package controller;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Card;
import util.ClientConnection;


public class StageOpponentController implements Initializable {


    private static final int BOARD_MACE = 3;

    @FXML
    private AnchorPane root;

    @FXML
    private GridPane grid;

    @FXML
    private Button btnPlay1;

    @FXML
    private Button btnPlay2;

    @FXML
    private Button btnPlay3;

    private Card[][] cardMace = new Card[BOARD_MACE][BOARD_MACE];


    ClientConnection client = ClientConnection.getInstance();

    PlayerSinglenton player = PlayerSinglenton.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChargePlayer();
    }

    void ChargePlayer(){
        client.sendMessageToServer(player.getName()+":get_rival");
        String response = client.responseMessageToServer();
        String []aux = response.split("\n");
        btnPlay1.setText(aux[0]);
        btnPlay2.setText(aux[1]);
        btnPlay3.setText(aux[2]);
    }

    void ShowMace(String data){
        client.sendMessageToServer(data +":get_cards");
        String response = client.responseMessageToServer();
        String []aux = response.split("\n");

        int index = 0;
        for(int i=0; i < BOARD_MACE; i++){
            for(int j=0; j < BOARD_MACE; j++){
                if(index > 6){
                    cardMace[i][j]= new Card("BackCard","Cerezo", "/images/cards/backcard1.png",0,false);
                    continue;
                }
                cardMace[i][j]= new Card("BackCard","Cerezo", aux[index],0,false);
                index++;
            }
        }
    }

    public void fillMace(){
        root.getChildren().remove(grid);
        grid = new GridPane();
        for(int i = 0; i < BOARD_MACE; i++){
            for(int j = 0; j < BOARD_MACE; j++){
                String path = cardMace[i][j].getExtension();
                ImageView img = new ImageView(path);
                img.setFitWidth(70);
                img.setFitHeight(110);
                grid.add(img, j, i);
            }
        }
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setLayoutX(120);
        grid.setLayoutY(50);

        root.getChildren().add(grid);
    }

    @FXML
    void ShowPlayer1(ActionEvent event) {
        ShowMace(btnPlay1.getText());
        fillMace();
    }

    @FXML
    void ShowPlayer2(ActionEvent event) {
        ShowMace(btnPlay2.getText());
        fillMace();
    }

    @FXML
    void ShowPlayer3(ActionEvent event) {
        ShowMace(btnPlay3.getText());
        fillMace();
    }
}
