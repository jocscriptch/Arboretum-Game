package  controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Card;

import java.util.Stack;
import util.*;

public class BoardGameController {
    private static final int BOARD_SIZE = 6;  // ajusta a tu gusto


    @FXML
    private AnchorPane principal;
    @FXML
    private GridPane grid;
    @FXML
    private Button btnVerRival;
    @FXML
    private Button btnVerMazo;
    @FXML
    private Button btnEnviar;
    @FXML
    private TextField txtColumn;
    @FXML
    private TextField txtRow;


    private Card[][] cardBoard = new Card[6][6];

    private int countFirst = 0;
    private int row = 0;
    private int colum = 0;

    Stack<Integer> rows = new Stack();
    Stack<Integer> colums = new Stack();


    public void initialize() {
        // Establecer la imagen de fondo
        initCardMatriz();
        initGridPane();
    }
    public void initGridPane(){
        principal.getChildren().remove(grid);

        Image image = new Image("/images/boardImage.jpg");  // sustituye "image_path" por la ruta a tu imagen
        /*BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO,
                        BackgroundSize.AUTO,
                        true,
                        true,
                        true,
                        true));
        grid.setBackground(new Background(backgroundImage));*/
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPrefSize(800,500);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int row = i+1;
                int colum = j+1;
                //Text t = new Text("F:"+row+"|C: "+colum);
                //VBox vbox =  new VBox(t);
                String extension = cardBoard[i][j].getExtension();
                ImageView img = new ImageView(extension);
                img.setFitWidth(60);
                img.setFitHeight(100);
                //vbox.getChildren().add(img);
                //grid.add(vbox, j, i);
                grid.add(img, j, i);
            }
        }
        principal.getChildren().add(grid);
        grid.getChildren().forEach(node -> {
            node.setOnMouseClicked(event -> {
                colum = GridPane.getColumnIndex(node);
                row = GridPane.getRowIndex(node);
            });
        });
    }

    public void initCardMatriz(){
        for(int i=0; i< BOARD_SIZE; i++){
            for(int j=0; j< BOARD_SIZE; j++){
                cardBoard[i][j] = new Card("BackCard","Cerezo","/images/cards/backcard1.png",0,false);
            }
        }
    }

    public boolean isBoarder(int row, int colum){
        if(row == 0 || row == BOARD_SIZE - 1 || (row < BOARD_SIZE && colum == 0) || (row < BOARD_SIZE && colum == BOARD_SIZE - 1))
            return true;
        return false;
    }
    public boolean isDiagonal(int row, int colum){
        if (Math.abs(row - rows.lastElement()) == 1 && Math.abs(colum - colums.lastElement()) == 1) {
            return true;
        }
        return false;
    }

    public boolean isOrtogonal(int row, int colum){
        for(int i= rows.size()-1; i >= 0; i--){
            if((row  == rows.elementAt(i) && Math.abs(colum - colums.elementAt(i)) == 1) || (colum == colums.elementAt(i)
                    && Math.abs(row - rows.elementAt(i)) == 1)){
                return true;
            }
        }
        return false;
    }

    @FXML
    void SendGarden(ActionEvent event) {
        if(isBoarder(row, colum) || countFirst > 0){
            if(!rows.isEmpty() && !colums.isEmpty()){
                if(!isDiagonal(row, colum) && isOrtogonal(row,colum) || countFirst == 0) {
                    rows.push(row);
                    colums.push(colum);
                    countFirst += 1;
                    cardBoard[row][colum].setExtension("/images/cards/Cornejo1.png");
                    cardBoard[row][colum].setPlaced(true);
                }
                if(!isDiagonal(row, colum) && !isOrtogonal(row,colum)){
                    rows.push(row);
                    colums.push(colum);
                    countFirst += 1;
                    cardBoard[row][colum].setExtension("/images/cards/Arce1.png");
                    cardBoard[row][colum].setPlaced(true);
                }
            }else{
                rows.push(row);
                colums.push(colum);
                countFirst+=1;
                cardBoard[row][colum].setExtension("/images/cards/Jacaranda1.png");
                cardBoard[row][colum].setPlaced(true);
            }
        }else{

        }
        initGridPane();
    }
    //asignado al boton rival
    @FXML
    void verRival(ActionEvent event){
        System.out.println("Ejecutando");
        ClientConnection client = ClientConnection.getInstance();
        System.out.println(txtRow.getText());
        client.sendMessageToServer(txtRow.getText());
        System.out.println(client.responseMessageToServer());
    }
}
