package hellofx;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EleccionController {
  @FXML
  private StackPane fondo;
  @FXML
  private Label textElection;
  @FXML
  private Button circle;
  @FXML
  private Button equis;
  private Stage stage;

  public static String skinIA;
  public static String skinJugador;

  @FXML
  public void initialize(){

    textElection.setText("ELIGE TU SKIN");
    textElection.setTextFill(Color.rgb(255, 163, 0));
    textElection.setFont(new Font("Neon", 70));
    textElection.setStyle("-fx-background-color: black");
    fondo.setStyle("-fx-background-image: url('/resources/fondo1.jpg');" +
                   "-fx-background-size: cover;" +
                   "-fx-background-repeat: no-repeat;");
    
    circle.setStyle("-fx-background-color: black;");
    equis.setStyle("-fx-background-color: black;");

    Image iconImage = new Image(getClass().getResourceAsStream("/resources/circle.jpeg"));

    ImageView iconView = new ImageView(iconImage);
    iconView.setFitWidth(150);  
    iconView.setFitHeight(150);

    circle.setGraphic(iconView);
    botonesIluminados(circle, "black", "blue");

    Image iconImage1 = new Image(getClass().getResourceAsStream("/resources/equis.png"));

    ImageView iconView1 = new ImageView(iconImage1);
    iconView1.setFitWidth(150);  
    iconView1.setFitHeight(150);

    equis.setGraphic(iconView1);
    botonesIluminados(equis, "black", "rgb(255, 5, 52)");
    
  }
  @FXML
  public void cCircle(){
    skinJugador = "circle.jpeg";
    skinIA = "equis.png";
    try{
      Parent juegoRoot = FXMLLoader.load(getClass().getResource("/resources/Juego.fxml"));
      Scene juegoScene = new Scene(juegoRoot, 600, 600);
      stage = (Stage) circle.getScene().getWindow();
      
      stage.setScene(juegoScene);

    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
  @FXML
  public void cEquis(){
    skinJugador = "equis.png";
    skinIA = "circle.jpeg";
    try{
    Parent juegoRoot = FXMLLoader.load(getClass().getResource("/resources/Juego.fxml"));
      Scene juegoScene = new Scene(juegoRoot, 600, 600);
      stage = (Stage) circle.getScene().getWindow();
      
      stage.setScene(juegoScene);
      
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  private void botonesIluminados(Button button, String color1, String color2) {

    button.setOnMouseEntered(event -> {
        button.setStyle("-fx-background-color: " + color2 + ";");
    });

    button.setOnMouseExited(event -> {
        button.setStyle("-fx-background-color: " + color1 + ";");
    });
  }
}
