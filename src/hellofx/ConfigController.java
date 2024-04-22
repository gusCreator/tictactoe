package hellofx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class ConfigController {
  @FXML
  private StackPane fondo;
  @FXML
  private HBox content; 
  @FXML
  private VBox buttons;
  @FXML
  private Button name;
  @FXML
  private Button us;
  @FXML
  private Scene myScene;

  @FXML
  public void initialize(){

    fondo.setStyle("-fx-background-image: url('/resources/fondo1.jpg');" +
                   "-fx-background-size: cover;" +
                   "-fx-background-repeat: no-repeat;");


    name.setFont(Font.font("Neon", 25));
    name.setTextFill(Color.BLACK);
    name.setStyle("-fx-background-color: rgb(46, 248, 160);");
    botonesIluminados(name, "rgb(46, 248, 160);", "green");

    us.setFont(Font.font("Neon", 25));
    us.setTextFill(Color.BLACK);
    us.setStyle("-fx-background-color: rgb(46, 248, 160);");
    botonesIluminados(us, "rgb(46, 248, 160);", "green");


    try {
      VBox forName = new VBox();
      forName.setAlignment(Pos.CENTER);
      Stage stage = (Stage) name.getScene().getWindow();
      myScene = new Scene(forName, 600, 600);
      stage.setScene(myScene);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  @FXML
  public void cName(){
    
  }
  @FXML
  public void cUs(){

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
