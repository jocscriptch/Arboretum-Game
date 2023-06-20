package  controller;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class BoardGameController {
    private static final int BOARD_SIZE = 6;  // ajusta a tu gusto

    @FXML
    private GridPane grid;

    public void initialize() {
        // Establecer la imagen de fondo
        Image image = new Image("/images/boardImage.jpg");  // sustituye "image_path" por la ruta a tu imagen
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO,
                        BackgroundSize.AUTO,
                        true,
                        true,
                        true,
                        true));
        grid.setBackground(new Background(backgroundImage));
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                ImageView img = new ImageView("/images/cards/Cerezo8.png");
                img.setFitWidth(80);
                img.setFitHeight(80);
                grid.add(img, j, i);

                /*
                Button button = new Button();
                button.setPrefSize(90, 90);  // tamaño de las "cartas"
                button.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-border-color: black; -fx-border-width: 1;");
                grid.add(button, j, i);
                */
            }
        }
    }
}
