package hellofx;

import java.io.IOException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EmpateController {
  @FXML
  private StackPane fondo;
  @FXML
  private VBox content;
  @FXML
  private Label title;
  @FXML
  private Label text;
  @FXML
  private Button volver;

  private String[] frases = {
    "EL EMPATE ES MEJOR DE LO\nQUE PIENSAS",
    "A EL NO LE GUSTA EMPATAR",
    "RESISTE, AUN NO ES PODEROSO",
    "IRA A POR TI",
    "NUNCA SACIA SU HAMBRE"
  };

  private Random rand = new Random();

  @FXML
  public void initialize(){
    content.setPadding(new Insets(30, 0, 30, 0));
    
    customText(title, "EMPATE!", 70, Color.rgb(197, 1, 226));

    customText(text, frases[rand.nextInt(frases.length)], 50, Color.rgb(1, 196, 231));

    volver.setFont(Font.font("Neon", 25));
    volver.setTextFill(Color.BLACK);
    volver.setStyle("-fx-background-color: rgb(46, 248, 160);");
    botonesIluminados(volver, "rgb(46, 248, 160);", "green" );

    fondo.setStyle("-fx-background-image: url('/resources/fondo1.jpg');" +
                   "-fx-background-size: cover;" +
                   "-fx-background-repeat: no-repeat;");
  }

  @FXML
  public void cVolver(){
    try {
      Parent electionRoot = FXMLLoader.load(getClass().getResource("/resources/Inicio.fxml"));
      Scene inicioScene = new Scene(electionRoot, 600, 600);
      Stage stage = (Stage) volver.getScene().getWindow();
      stage.setScene(inicioScene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void customText(Label label, String text, int tamaño, Paint style){
    label.setText(text);
    label.setFont(Font.font("Neon", tamaño));
    label.setStyle("-fx-background-color: black;");
    label.setTextFill(style);
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