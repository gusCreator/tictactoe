package hellofx;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class InicioController {
  @FXML
  private Button conf;
  @FXML
  private Button inicio;
  @FXML
  private Label bienvenido;
  @FXML
  private Label title1;
  @FXML
  private Label title2;
  @FXML
  private StackPane fondo;
  @FXML
  private VBox content;
  private Stage stage;
  private String[] messages = {
    "¿PUEDES VENCER A STICH?",
    "NO DEJES QUE ENTRE EN TU CABEZA",
    "CUIDA CADA UNO DE TUS MOVIMIENTOS",
    "¿DE VERDAD QUIERES INTENTARLO?",
    "QUE NO TE SIGA",
    "...",
    "TE OBSERVA",
    "EL SABE DONDE ESTAS",
    "NO MIRES DETRAS DE TI",
    "EL YA ESTA AQUI"
  };

  @FXML
  public void initialize(){
    content.setPadding(new Insets(30, 0, 70, 0));
    content = null;
    
    title1.setText("TicTacToe");
    title1.setTextFill(Color.rgb(197, 1, 226));
    title1.setFont(Font.font("Neon", 170));
    title1.setStyle("-fx-background-color: transparent");
    title1 = null;

    title2.setText("STICH");
    title2.setTextFill(Color.rgb(1, 196, 231));
    title2.setFont(Font.font("Neon", 190));
    title2.setStyle("-fx-background-color: transparent");
    title2 = null;


    int rango = (int)(Math.random() * 1000);
    String selectMessage = "";
    if(rango <= 150)
      selectMessage = messages[0];
    else if(rango <= 200)
      selectMessage = messages[1];
    else if(rango <= 400)
      selectMessage = messages[2];
    else if(rango <= 600)
      selectMessage = messages[3];
    else if(rango <= 650)
      selectMessage = messages[4];
    else if(rango <= 850)
      selectMessage = messages[5];
    else if(rango <= 900)
      selectMessage = messages[6];
    else if(rango <= 950)
      selectMessage = messages[7];
    else if(rango <= 975)
      selectMessage = messages[8];
    else
      selectMessage = messages[9];
    
    bienvenido.setFont(Font.font("Neon", 40));
    bienvenido.setStyle("-fx-background-color: transparent");
    bienvenido.setText(selectMessage);
    bienvenido = null;
    selectMessage = null;

    Image iconImage = new Image(getClass().getResourceAsStream("/resources/config.png"));

    ImageView iconView = new ImageView(iconImage);
    iconView.setFitWidth(50);  
    iconView.setFitHeight(50);

    conf.setGraphic(iconView);
    iconImage = null; iconView = null;
    inicio.setFont(new Font("Lato Black", 30));

    conf.setCursor(Cursor.HAND);
    conf.setStyle("-fx-background-color: rgb(46, 248, 160);");
    inicio.setCursor(Cursor.HAND);
    inicio.setStyle("-fx-background-color: rgb(46, 248, 160);");
    inicio.setTextFill(Color.BLACK);
    inicio.setFont(new Font("Neon", 33));


    botonesIluminados(conf);
    botonesIluminados(inicio);
    

    fondo.setStyle("-fx-background-image: url('/resources/fondo1.jpg');" +
                   "-fx-background-size: cover;" +
                   "-fx-background-repeat: no-repeat;");
    fondo = null;
    System.gc();
  }
  @FXML
  public void cConf() {
    
  }
  @FXML
  public void cInicio(){
    try {
      Parent electionRoot = FXMLLoader.load(getClass().getResource("/resources/Eleccion.fxml"));
      Scene juegoScene = new Scene(electionRoot, 600, 600);
      stage = (Stage) inicio.getScene().getWindow();
      inicio = null;
      conf = null;
      stage.setScene(juegoScene);
      stage = null;
      System.gc();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void botonesIluminados(Button button) {

    button.setOnMouseEntered(event -> {
        button.setStyle("-fx-background-color: green;");
    });

    button.setOnMouseExited(event -> {
        button.setStyle("-fx-background-color: rgb(46, 248, 160);");
    });
  }
}
