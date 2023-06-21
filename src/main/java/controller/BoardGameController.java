package  controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.input.MouseEvent;
import model.Card;
import util.ClientConnection;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;
import com.google.gson.Gson;

public class BoardGameController {
    private static final int BOARD_SIZE = 7;  // ajustar
    private static final int BOARD_MACE = 3;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane main;

    @FXML
    private GridPane grid;

    @FXML
    private Button btnVerRival;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnRobar;

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

    private double initialX;
    private double initialY;

    Stack<Integer> rows = new Stack();
    Stack<Integer> colums = new Stack();

    //Global Instances
    ClientConnection client = ClientConnection.getInstance();
    PlayerSinglenton player = PlayerSinglenton.getInstance();

    private StageOpponentController stageOpponentController;

    private Stage stageOpponent;



    public void initialize() {

        initCardMatriz();
        initGridPane();

        btnEnviar.setLayoutX(610);
        btnEnviar.setLayoutY(2);
        btnVerRival.setLayoutX(610);
        btnVerRival.setLayoutY(60);
        btnRobar.setLayoutX(610);
        btnRobar.setLayoutY(120);
        labelPlayer.setLayoutX(750);
        labelPlayer.setLayoutY(10);

        Mace();
        fillMace();

        labelPlayer.setText(player.getName());
        String playerName = labelPlayer.getText();
        labelPlayer.setText(playerName.toUpperCase());

        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);
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
        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);
    }

    //Metodos ScrollPane
    private void onMousePressed(MouseEvent event) {
        initialX = event.getSceneX();
        initialY = event.getSceneY();
    }

    private void onMouseDragged(MouseEvent event) {
        double offsetX = event.getSceneX() - initialX;
        double offsetY = event.getSceneY() - initialY;

        grid.setTranslateX(grid.getTranslateX() + offsetX);
        grid.setTranslateY(grid.getTranslateY() + offsetY);

        initialX = event.getSceneX();
        initialY = event.getSceneY();
    }

    public void initCardMatriz(){
        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);
        for(int i=0; i< BOARD_SIZE; i++){
            for(int j=0; j< BOARD_SIZE; j++){
                cardBoard[i][j] = new Card("BackCard","Cerezo","/images/cards/backcard1.png",0,false);
            }
        }
    }

    public boolean isBoarder(int row, int colum){
        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);
        if(row == 0 || row == BOARD_SIZE - 1 || (row < BOARD_SIZE && colum == 0) || (row < BOARD_SIZE && colum == BOARD_SIZE - 1))
            return true;
        return false;
    }
    public boolean isDiagonal(int row, int colum){
        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);
        if (Math.abs(row - rows.lastElement()) == 1 && Math.abs(colum - colums.lastElement()) == 1) {
            return false;
        }
        return true;
    }

    public boolean isOrtogonal(int row, int colum){
        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);
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
        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);
    }
    //asignado al boton rival
    @FXML
    public void verRival() throws IOException {
        System.out.println("Viendo Rivales");
        client.sendMessageToServer(player.getName()+":get_rival");
        String []aux = client.responseMessageToServer().split("\n");
        for(String a: aux){
            System.out.println(a);
        }
        //cardBoard[2][2].setExtension(aux[3]);
        //initGridPane();*/

        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StageOpponent.fxml"));
        AnchorPane ap = loader.load();
        stageOpponentController = loader.getController();
        Scene scene = new Scene(ap);
        stageOpponent = new Stage();

        stageOpponent.setTitle("Ventana Oponente");
        stageOpponent.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/player.png"))));
        stageOpponent.setScene(scene);
        stageOpponent.initOwner(root.getScene().getWindow());
        stageOpponent.initModality(Modality.WINDOW_MODAL);
        stageOpponent.initStyle(StageStyle.DECORATED);
        stageOpponent.setResizable(false);
        stageOpponent.setOnCloseRequest((WindowEvent e) ->{

            root.setEffect(null);
        });
        stageOpponent.setOnHidden((WindowEvent e)->{
            root.setEffect(null);
        });

        stageOpponent.showAndWait();
    }

    public void deckCard() {

        System.out.println("hola");

    }

    public void Mace(){
        System.out.println(player.getName());
        client.sendMessageToServer(player.getName()+":get_cards");
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
        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);
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
        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);
    }

    /* metodo turno
    @FXML
    void PassTurn(ActionEvent event){
        System.out.println(player.getName());
        client.sendMessageToServer(player.getName()+":get_player");
        String response = client.responseMessageToServer();
        String []aux = response.split("\n");
        for (String items: aux ){
            System.out.println(aux);
        }
    }*/
    @FXML
    void StealOneCardMace(ActionEvent event){
        if ((row == -1 && colum == -1) && ( rowMace == -1 && columMace == -1)){
            System.out.println("No ha hecho movs");
        }else{
            System.out.println("Entrando a robar");
            client.sendMessageToServer(player.getName()+":stealCardMace");
            String response = client.responseMessageToServer();
            System.out.println(response);
            cardMace[rowMace][columMace].setExtension(response);
            fillMace();
        }
        grid.setOnMousePressed(this::onMousePressed);
        grid.setOnMouseDragged(this::onMouseDragged);

    }
}
