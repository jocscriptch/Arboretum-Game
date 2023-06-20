package  controller;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Window;
import javafx.util.Duration;
import model.Card;
import java.util.Stack;

public class BoardGameController {
    private static final int BOARD_SIZE = 7;  // ajustar

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

    private Card[][] cardBoard = new Card[7][7];

    private int countFirst = 0;
    private int row = -1;
    private int colum = -1;

    Stack<Integer> rows = new Stack();
    Stack<Integer> colums = new Stack();


    public void initialize() {

        initCardMatriz();
        initGridPane();

        btnEnviar.setLayoutX(500);
        btnEnviar.setLayoutY(2);
        btnVerMazo.setLayoutX(500);
        btnVerMazo.setLayoutY(60);
        btnVerRival.setLayoutX(500);
        btnVerRival.setLayoutY(120);

        deckCard();

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
        if(isBoarder(row, colum) || countFirst > 0){
            if(!rows.isEmpty() && !colums.isEmpty()){
                if(isDiagonal(row, colum) && isOrtogonal(row,colum) || countFirst == 0) {
                    rows.push(row);
                    colums.push(colum);
                    countFirst += 1;
                    cardBoard[row][colum].setExtension("/images/cards/Cornejo2.png");
                    cardBoard[row][colum].setPlaced(true);
                }
                if(isDiagonal(row, colum) && !isOrtogonal(row,colum)){
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
        /*System.out.println("Ejecutando");
        ClientConnection client = ClientConnection.getInstance();
        System.out.println(txtRow.getText());
        client.sendMessageToServer(txtRow.getText());
        System.out.println(client.responseMessageToServer());*/
    }

    public void deckCard() {

    }
}
