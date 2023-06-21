package  controller;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Window;
import javafx.util.Duration;
import model.Card;
import util.ClientConnection;
import java.util.Stack;
import com.google.gson.Gson;

public class BoardGameController {
    private static final int BOARD_SIZE = 7;  // ajustar
    private static final int BOARD_MACE = 3;

    @FXML
    private AnchorPane principal;

    @FXML
    private AnchorPane root;

    @FXML
    private GridPane grid;

    @FXML
    private Button btnVerRival;

    @FXML
    private Button btnVerMazo;

    @FXML
    private Button btnEnviar;

    @FXML
    private Label labelPlayer;
    @FXML
    private AnchorPane anMace;

    @FXML
    private GridPane gridMace;


    private Card[][] cardBoard = new Card[BOARD_SIZE][BOARD_SIZE];
    private Card[][] cardMace = new Card[BOARD_MACE][BOARD_MACE];

    private int countFirst = 0;
    private int row = -1;
    private int colum = -1;
    private int rowMace = -1;
    private int columMace = -1;


    Stack<Integer> rows = new Stack();
    Stack<Integer> colums = new Stack();

    //Global Instances
    //ClientConnection client = ClientConnection.getInstance();
    PlayerSinglenton player = PlayerSinglenton.getInstance();



    public void initialize() {

        initCardMatriz();
        initGridPane();

        btnEnviar.setLayoutX(500);
        btnEnviar.setLayoutY(2);
        btnVerMazo.setLayoutX(500);
        btnVerMazo.setLayoutY(60);
        btnVerRival.setLayoutX(500);
        btnVerRival.setLayoutY(120);

        Mace();
        fillMace();
    }

    public void initGridPane(){
        root.getChildren().remove(grid);

        grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

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
        root.getChildren().add(grid);

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
            return false;
        }
        return true;
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
        String card = cardMace[rowMace][columMace].getExtension();
        System.out.println(cardMace);
        cardMace[rowMace][columMace].setExtension("/images/cards/backcard1.png");
        if(isBoarder(row, colum) || countFirst > 0){
            if(!rows.isEmpty() && !colums.isEmpty()){
                if(isDiagonal(row, colum) && isOrtogonal(row,colum) || countFirst == 0) {
                    rows.push(row);
                    colums.push(colum);
                    countFirst += 1;
                    cardBoard[row][colum].setExtension(card/*"/images/cards/Cornejo2.png"*/);
                    cardBoard[row][colum].setPlaced(true);
                }
                if(isDiagonal(row, colum) && !isOrtogonal(row,colum)){
                    rows.push(row);
                    colums.push(colum);
                    countFirst += 1;
                    cardBoard[row][colum].setExtension( card/*"/images/cards/Arce1.png"*/);
                    cardBoard[row][colum].setPlaced(true);
                }
            }else{
                rows.push(row);
                colums.push(colum);
                countFirst+=1;
                cardBoard[row][colum].setExtension(card/*"/images/cards/Jacaranda1.png"*/);
                cardBoard[row][colum].setPlaced(true);
            }
        }else {

        }
        initGridPane();
        fillMace();
    }
    //asignado al boton rival
    @FXML
    void verRival(ActionEvent event){
        /*System.out.println("Ejecutando");
        client.sendMessageToServer("user1:get_cards");
        String []aux = client.responseMessageToServer().split("\n");
        cardBoard[2][2].setExtension(aux[3]);
        initGridPane();*/
    }

    public void deckCard() {

        System.out.println("hola");

    }

    public void Mace(){
        /*System.out.println(namePlayer.getName());
        client.sendMessageToServer(namePlayer.getName()+":get_cards");
        String response = client.responseMessageToServer();
        String []aux = response.split("\n");*/
        String []aux = {"/images/cards/Jacaranda1.png","/images/cards/Jacaranda2.png", "/images/cards/Jacaranda3.png","/images/cards/Jacaranda4.png",
                "/images/cards/Jacaranda5.png", "/images/cards/Jacaranda6.png", "/images/cards/Jacaranda7.png", "/images/cards/Jacaranda8.png",
                "/images/cards/Arce1.png", "/images/cards/Jacaranda6.png", "/images/cards/Jacaranda7.png", "/images/cards/Jacaranda8.png",};
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
        /*player.getCards().add(new Card("Carta","Tipo", "/images/cards/Jacaranda1.png", 3, false));
        player.getCards().add(new Card("Carta","Tipo", "/images/cards/Jacaranda2.png", 3, false));
        player.getCards().add(new Card("Carta","Tipo", "/images/cards/Jacaranda3.png", 3, false));
        player.getCards().add(new Card("Carta","Tipo", "/images/cards/Jacaranda4.png", 3, false));
        player.getCards().add(new Card("Carta","Tipo", "/images/cards/Jacaranda5.png", 3, false));
        player.getCards().add(new Card("Carta","Tipo", "/images/cards/Jacaranda6.png", 3, false));
        player.getCards().add(new Card("Carta","Tipo", "/images/cards/Jacaranda7.png", 3, false));
        player.getCards().add(new Card("Carta","Tipo", "/images/cards/Jacaranda8.png", 3, false));
        player.getCards().add(new Card("Carta","Tipo", "/images/cards/Arce1.png", 3, false));*/

    }

    public void fillMace(){
        anMace.getChildren().remove(gridMace);
        gridMace = new GridPane();
        for(int i = 0; i < BOARD_MACE; i++){
            for(int j = 0; j < BOARD_MACE; j++){
                String path = cardMace[i][j].getExtension();
                ImageView img = new ImageView(path);
                img.setFitWidth(60);
                img.setFitHeight(100);
                gridMace.add(img, j, i);
            }
        }
        gridMace.setVgap(5);
        gridMace.setHgap(5);
        gridMace.setLayoutX(115);
        gridMace.setLayoutY(0);


        anMace.getChildren().add(gridMace);
        gridMace.getChildren().forEach(node -> {
            node.setOnMouseClicked(event -> {
                columMace = GridPane.getColumnIndex(node);
                rowMace = GridPane.getRowIndex(node);
                System.out.println(columMace+""+rowMace);
            });
        });
    }
}
