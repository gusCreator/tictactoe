package hellofx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JuegoController {
  @FXML
  private Button b00;
  @FXML
  private Button b01;
  @FXML
  private Button b02;
  @FXML
  private Button b10;
  @FXML
  private Button b11;
  @FXML
  private Button b12;
  @FXML
  private Button b20;
  @FXML
  private Button b21;
  @FXML
  private Button b22;

  protected Button[][] buts;
  @FXML
  private StackPane fondo;
  @FXML
  private Label turnos;
  @FXML
  private Label frase;
  @FXML
  private VBox content;


  private Image aspa = new Image(getClass().getResourceAsStream("/resources/equis.png"));

  private Image circle = new Image(getClass().getResourceAsStream("/resources/circle.jpeg"));

  private Image player;
  private Image stichIA;

  private int TERMINO; //0:jugando 1: player 2: stich 3:empate
  private static final int PLAYER_GANO = 1;
  private static final int STICH_GANO = 2;
  private static final int EMPATE = 3;
  private static final int JUGANDO = 0;

  private boolean juegoTerminado = false;
  private int newWindow;

  private static String skinIA;
  @FXML
  private Label stich;
  @FXML
  private VBox chat; 
  @FXML
  private GridPane tablero;

  private List<Label> chats = new ArrayList<Label>(){{
    add(new Label("HUMANO TONTO, ¿CREES\n QUE ME GANARAS CON\n ESE ESTILO DE JUEGO"));
    add(new Label("DOMINARE EL MUNDO"));
    add(new Label("DEJA QUE TE CONTROLE,\n SE LO QUE PIENSAS"));
    add(new Label("MI MENTE ES MEJOR QUE\n LA TUYA"));
    add(new Label("SE LO QUE HARAS, CUIDA\n TUS ESPALDAS"));
    add(new Label("VIGILO TODO LO QUE\n HACES"));
    add(new Label("TODO ESTA FRIAMENTE\n CALCULADO"));
  }};
  private Map<Button, Boolean> pressed = new HashMap<>();

  private boolean turnoIA; //true: le toca a la IA, false: le toca a player 

  @FXML
  public void initialize(){
    skinIA = EleccionController.skinIA;
    buts = new Button[3][3];
    buts[0][0] = b00; buts[0][1] = b01; buts[0][2] = b02;
    buts[1][0] = b10; buts[1][1] = b11; buts[1][2] = b12;
    buts[2][0] = b20; buts[2][1] = b21; buts[2][2] = b22;
 
    for(int i = 0; i < chats.size(); i++)
      customChat(chats.get(i));

    content.setPadding(new Insets(20, 0, 30, 0));

    fondo.setStyle("-fx-background-image: url('/resources/fondo1.jpg');" +
                   "-fx-background-size: cover;" +
                   "-fx-background-repeat: no-repeat;");

    frase.setText("¿QUE TAL HUMANO, VIENES\n POR UNA NUEVA DERROTA?");
    frase.setTextFill(Color.rgb(253, 163, 0));
    frase.setFont(Font.font("Neon", 20));

    stich.setText("STICH: ");
    stich.setFont(Font.font("Neon", 20));
    stich.setTextFill(Color.rgb(174, 61, 255));
        
    if(skinIA.equals("circle.jpeg")){
      turnos.setText("Turno de Stich");
      turnoIA = true;
      stichIA = circle;
      player = aspa;

    }else{ 
      turnos.setText("Turno de Player");
      turnoIA = false;
      stichIA = aspa;
      player = circle;
    }

    turnos.setTextFill(Color.rgb(0, 40, 255));
    //turnos.setTextFill(Color.rgb(255, 5, 52));
    turnos.setFont(Font.font("Neon", 100));

    b00.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: black" );
    b00.setMinSize(100, 100);
    botonesIluminados(b00, "black", "rgb(233, 255, 136)");
    b01.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: black");
    b01.setMinSize(100, 100);
    botonesIluminados(b01, "black", "rgb(233, 255, 136)");
    b02.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: black");
    b02.setMinSize(100, 100);
    botonesIluminados(b02, "black", "rgb(233, 255, 136)");

    b10.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: black");
    b10.setMinSize(100, 100);
    botonesIluminados(b10, "black", "rgb(233, 255, 136)");
    b11.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: black");
    b11.setMinSize(100, 100);
    botonesIluminados(b11, "black", "rgb(233, 255, 136)");
    b12.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: black");
    b12.setMinSize(100, 100);
    botonesIluminados(b12, "black", "rgb(233, 255, 136)");

    b20.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: black");
    b20.setMinSize(100, 100);
    botonesIluminados(b20, "black", "rgb(233, 255, 136)");
    b21.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: black");
    b21.setMinSize(100, 100);
    botonesIluminados(b21, "black", "rgb(233, 255, 136)");
    b22.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: black");
    b22.setMinSize(100, 100);
    botonesIluminados(b22, "black", "rgb(233, 255, 136)");
    System.gc();
    if(turnoIA){
      enJuego();
      turnoIA = false;
    }

  }

  @FXML
  public void cB00(){
    actionButton(b00);
  }
  @FXML
  public void cB01(){
    actionButton(b01);
  }
  @FXML
  public void cB02(){
    actionButton(b02);
  }
  @FXML
  public void cB10(){
    actionButton(b10);
  }
  @FXML
  public void cB11(){
    actionButton(b11);
  }
  @FXML
  public void cB12(){
    actionButton(b12);
  }
  @FXML
  public void cB20(){
    actionButton(b20);
  }
  @FXML
  public void cB21(){
    actionButton(b21);
  }
  @FXML
  public void cB22(){
    actionButton(b22);
  }
  private void botonesIluminados(Button button, String color1, String color2) {

    button.setOnMouseEntered(event -> {
        button.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: " + color2);
    });

    button.setOnMouseExited(event -> {
        button.setStyle("-fx-border-color: rgb(0, 255, 40); -fx-border-width: 2px; -fx-background-color: " + color1);
    });
  }
  private void customChat(Label frase){
    frase.setTextFill(Color.rgb(253, 163, 0));
    frase.setFont(Font.font("Neon", 20));
  }
  private void customImage(Image img, Button but){
    ImageView show = new ImageView(img);
    show.setFitHeight(75);
    show.setFitWidth(75);
    but.setGraphic(show);
  }
  private void addChat(){
    HBox add = new HBox();
    Label stich = new Label();
    stich.setText("STICH: ");
    stich.setFont(Font.font("Neon", 20));
    stich.setTextFill(Color.rgb(174, 61, 255));
    int i = getIndex(chats.size());
    add.getChildren().addAll(stich, chats.get(i));
    chat.getChildren().addAll(add);
    chats.remove(i);
  }

  private int enJuego(){
    
    boolean ganador = ganador(pressed);
    if(ganador){   
      if(TERMINO == PLAYER_GANO)
        return PLAYER_GANO;
      else 
        return STICH_GANO;
    }
    boolean heEvitado = heEvitado(pressed);
    if(heEvitado)
      return JUGANDO;
    boolean loPuseEsquina = loPuseEsquina(pressed);
    if(loPuseEsquina)
      return JUGANDO;
    boolean loPuseCentro = loPuseCentro(pressed);
    if(loPuseCentro)
      return JUGANDO;
    boolean loPuseLado = loPuseLado(pressed);
    if(loPuseLado)
      return JUGANDO;
    return EMPATE;
  }
  private void actionButton(Button b){
    if(!pressed.containsKey(b) && !juegoTerminado){
      
      if(turnoIA) {
        customImage(stichIA, b);
        turnos.setText("Turno de Player");
        turnoIA = false; 
        
      }else{
        customImage(player, b);
        addChat();
        turnos.setText("Turno de Stich");
        turnoIA = true;
        pressed.put(b, false);
        if(!juegoTerminado){
          newWindow = enJuego();
          if(newWindow != 0){
            juegoTerminado = true;        
          }
      }
        
      }
      if(pressed.size() == 9){
        juegoTerminado = true;
        if(newWindow == JUGANDO)
          newWindow = EMPATE;
      }
      
      if(juegoTerminado){
        turnos.setFont(Font.font("Neon", 70));
        switch(newWindow){
          case STICH_GANO:
            turnos.setText("STICH TE HA DERROTADO");
            break;
          case PLAYER_GANO:
            turnos.setText("LE HAS GANADO A STICH!");
            break;
          case EMPATE:
            turnos.setText("ES UN EMPATE");
            break;
        }

        
        Button continuar = new Button("CONTINUAR");
        continuar.setFont(Font.font("Neon", 20));
        continuar.setCursor(Cursor.HAND);
        continuar.setStyle("-fx-background-color: rgb(46, 248, 160);");
        continuar.setTextFill(Color.BLACK);
        botonesIluminados(continuar, "rgb(46, 248, 160);", "green" );

        HBox add = new HBox(continuar);
        add.setPadding(new Insets(20, 0, 0, 0));
        add.setAlignment(Pos.CENTER);
        chat.getChildren().add(add);
        continuar.setOnAction(event -> {
          try {
            Parent electionRoot; 
            switch(newWindow){
              case STICH_GANO:
                electionRoot = FXMLLoader.load(getClass().getResource("/resources/StichWin.fxml"));
                break;
              case PLAYER_GANO:
                electionRoot = FXMLLoader.load(getClass().getResource("/resources/PlayerWin.fxml"));
                break;
              default:
                electionRoot = FXMLLoader.load(getClass().getResource("/resources/Empate.fxml"));
                break;
            }

            Scene juegoScene = new Scene(electionRoot, 600, 600);
            Stage stage = (Stage) continuar.getScene().getWindow();
            stage.setScene(juegoScene);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });


      }
      
    }
  }
  private boolean ganador(Map<Button, Boolean> tablero){
    //comprobamos para filas
    int tresEnRayaIAF = 0, tresEnRayaPlayerF = 0;
    for(int i = 0; i < 3; i++){
      boolean vacio = false, unSoloVacio = false;
      Button paraGanar = null;
      for(int u = 0; u < 3; u++){
        vacio = !tablero.containsKey(buts[i][u]);
        if(!vacio){
          if(tablero.get(buts[i][u]))
            tresEnRayaIAF++;
          else
            tresEnRayaPlayerF++;
        }
        if(vacio){
          paraGanar = buts[i][u];
          unSoloVacio = true;
        }
      }
      if(tresEnRayaPlayerF == 3){
        TERMINO = PLAYER_GANO;
        return true;
      } 
      if(unSoloVacio && tresEnRayaIAF == 2){
        paraGanar.fire();
        tablero.put(paraGanar, true);
        TERMINO = STICH_GANO;
        return true;
      }    
      tresEnRayaIAF = 0;
      tresEnRayaPlayerF = 0;
    }
    //comprobamos para columnas
    int tresEnRayaIAC = 0, tresEnRayaPLayerC = 0;
    for(int i = 0; i < 3; i++){
      boolean vacio = false, unSoloVacio = false;
      Button paraGanar = null;
      for(int u = 0; u < 3; u++){
        vacio = !tablero.containsKey(buts[u][i]);
        if(!vacio){
          if(tablero.get(buts[u][i]))
            tresEnRayaIAC++;
          else
            tresEnRayaPLayerC++;
        }
        if(vacio){
          paraGanar = buts[u][i];
          unSoloVacio = true;
        }
      }
      if(tresEnRayaPLayerC == 3){
        TERMINO = PLAYER_GANO;
        return true;
      }
      if(unSoloVacio && tresEnRayaIAC == 2){
        paraGanar.fire();
        tablero.put(paraGanar, true);
        TERMINO = STICH_GANO;
        return true;
      }
      tresEnRayaIAC = 0;
      tresEnRayaPLayerC = 0;
    }
    //comprobamos para diagonal
    int tresEnRayaIAD1 = 0, tresEnRayaPLayerD1 = 0;
    boolean unSoloVacio = false;
    Button paraGanar = null;
    for(int i = 0; i < 3; i++){
      boolean vacio = !tablero.containsKey(buts[i][i]);
      if(!vacio){
        if(tablero.get(buts[i][i]))
          tresEnRayaIAD1++;
        else
          tresEnRayaPLayerD1++;
      }
      if(vacio){
        paraGanar = buts[i][i];
        unSoloVacio = true;
      }
    }
    if(tresEnRayaPLayerD1 == 3){
      TERMINO = PLAYER_GANO;
      return true;
    }
    if(unSoloVacio && tresEnRayaIAD1 == 2){
      paraGanar.fire();
      tablero.put(paraGanar, true);
      TERMINO = STICH_GANO;
      return true;
    }
    //para segunda diagonal
    int tresEnRayaIAD2 = 0, tresEnRayaPlayerD2 = 0;
    Button paraGanarD1 = null;
    boolean vacio = !tablero.containsKey(b02), sureVacio = false;
    if(!vacio){
      if(tablero.get(b02))
        tresEnRayaIAD2++;
      else
        tresEnRayaPlayerD2++;
    }
    if(vacio){
      sureVacio = true;
      paraGanarD1 = b02;
    }
    vacio = !tablero.containsKey(b11);
    if(!vacio){
      if(tablero.get(b11))
        tresEnRayaIAD2++;
      else
        tresEnRayaPlayerD2++;
    }
    if(vacio){
      sureVacio = true;
      paraGanarD1 = b11;
    }
    vacio = !tablero.containsKey(b20);
    if(!vacio){
      if(tablero.get(b20))
        tresEnRayaIAD2++;
      else 
        tresEnRayaPlayerD2++;
    }
    if(vacio){
      sureVacio = true;
      paraGanarD1 = b20;
    }
    if(tresEnRayaPlayerD2 == 3){
      TERMINO = PLAYER_GANO;
      return true;
    }
    if(sureVacio && tresEnRayaIAD2 == 2){
      paraGanarD1.fire();
      tablero.put(paraGanarD1, true);
      TERMINO = STICH_GANO;
      return true;
    }
    return false;
  }
  private boolean heEvitado(Map<Button, Boolean> tablero){
    //comprobamos para filas
    int listoParaGanarF = 0;
    for(int i = 0; i < 3; i++){
      boolean vacio = false, unSoloVacio = false;
      Button evitar = null;
      for(int u = 0; u < 3; u++){
        vacio = !tablero.containsKey(buts[i][u]);
        if(!vacio && !tablero.get(buts[i][u])){
          listoParaGanarF++;
        }
        if(vacio){
          evitar = buts[i][u];
          unSoloVacio = true;
        }
      }
      if(unSoloVacio && listoParaGanarF == 2){
        evitar.fire();
        tablero.put(evitar, true);
        return true;
      }
      listoParaGanarF = 0;
    }
    //comprobamos para columnas
    int listoParaGanarC = 0;
    for(int i = 0; i < 3; i++){
      boolean vacio = false, unSoloVacio = false;
      Button evitar = null;
      for(int u = 0; u < 3; u++){
        vacio = !tablero.containsKey(buts[u][i]);
        if(!vacio && !tablero.get(buts[u][i])){
          listoParaGanarC++;
        }
        if(vacio){
          evitar = buts[u][i];
          unSoloVacio = true;
        }
      }
      if(unSoloVacio && listoParaGanarC == 2){
        evitar.fire();
        tablero.put(evitar, true);
        return true;
      }
      listoParaGanarC = 0;
    }
    //para la diagonal principal
    int listoParaGanarD = 0;
    boolean unSoloVacio = false;
    Button evitar = null;
    for(int i = 0; i < 3; i++){
      boolean vacio = !tablero.containsKey(buts[i][i]);
      if(!vacio && !tablero.get(buts[i][i])){
        listoParaGanarD++;
      }
      if(vacio){
        evitar = buts[i][i];
        unSoloVacio = true;
      }
    }
    if(unSoloVacio && listoParaGanarD == 2){
      evitar.fire();
      tablero.put(evitar, true);
      return true;
    }
    //para segunda diagonal
    int listoParaGanarD1 = 0;
    Button evitarD1 = null;
    boolean vacio = !tablero.containsKey(b02), sureVacio = false;
    if(!vacio && !tablero.get(b02)){
      listoParaGanarD1++;
    }
    if(vacio){
      sureVacio = true;
      evitarD1 = b02;
    }
    vacio = !tablero.containsKey(b11);
    if(!vacio && !tablero.get(b11)){
      listoParaGanarD1++;
    }
    if(vacio){
      sureVacio = true;
      evitarD1 = b11;
    }
    vacio = !tablero.containsKey(b20);
    if(!vacio && !tablero.get(b20)){
      listoParaGanarD1++;
    }
    if(vacio){
      sureVacio = true;
      evitarD1 = b20;
    }
    if(sureVacio && listoParaGanarD1 == 2){
      evitarD1.fire();
      tablero.put(evitarD1, true);
      return true;
    }
    return false;
  }
  private boolean loPuseEsquina(Map<Button, Boolean> tablero){
    List<Button> buttons = new ArrayList<>(){{
      add(b00);
      add(b02);
      add(b20);
      add(b22);
    }};  
    int i = 4, index = getIndex(i);
    Button b = buttons.get(index);
    while(tablero.containsKey(b)){
      buttons.remove(index);
      i--;
      if(i == 0)
        return false;
      index = getIndex(i);
      b = buttons.get(index);
    }
    b.fire();
    tablero.put(b, true);
    return true;
  }
  private boolean loPuseCentro(Map<Button, Boolean> tablero){
    if(!tablero.containsKey(b11)){
      b11.fire();
      tablero.put(b11, true);
      return true;
    }
    return false;
  }
  private boolean loPuseLado(Map<Button, Boolean> tablero){
    List<Button> buttons = new ArrayList<>(){{
      add(b01);
      add(b10);
      add(b12);
      add(b21);
    }}; 
    int i = 4, index = getIndex(i);
    Button b = buttons.get(index);
    while(tablero.containsKey(b)){
      buttons.remove(index);
      i--;
      if(i == 0)
        return false;
      index = getIndex(i);
      b = buttons.get(index);
    }
    b.fire();
    tablero.put(b, true);
    return true;
  }
  private int getIndex(int n){
    int i = (int)(Math.random() * 10);
    while(i < 0 || i >= n){
      i = (int)(Math.random() * 10);
    }
    return i;
  }
}
