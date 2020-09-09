



import java.awt.Color;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FightWindow {
    private ArrayList<String> register;
    private Label[] lifeCh;
    private Label[] stamiteCh;
    private Label[] imgChrPly;
    private Label registerView;
    private ArrayList<String> a;
    private ArrayList<Player> players;
    
    public FightWindow(ArrayList<Player> players) {
        this.players= players;
        this.register = new ArrayList<>();
        this.lifeCh=new Label[2];
        this.stamiteCh=new Label[2];
        this.imgChrPly=new Label[2];
        this.imgChrPly[0]=new Label();
        this.imgChrPly[1]= new Label();
        this.registerView= new Label();
        this.registerView.setStyle("-fx-border-color:black;");
        this.registerView.setAlignment(Pos.TOP_LEFT);
        setStyler(this.registerView,10,780,50);
        setImgCh(imgChrPly[0],"lol.jpg");
        setImgCh(imgChrPly[1],"lol.jpg");
         a= new ArrayList<>();
        a.add("name");
        a.add("apellido");
        a.add("nickname");
        a.add("100");
        a.add("30");
    }
    
    
    public Scene showView() {
        HBox v= new HBox();
        v.getChildren().add(getView());
        Scene t = new Scene(v,860,500);
        
        
        return t;
    }
    
    public Parent getView(){
       
        
       
        VBox view = new VBox();//la pantalla completa
        //formato de pantalla
        view.setSpacing(5);
        view.setStyle("-fx-border-color:black;");
        view.setPadding(new Insets(10, 10, 10, 10));
        //elementos dentro de la pantalla

        VBox challengersDatas = new VBox();//datos del jugadores y personaje
        BorderPane buttonsPlayersAction= new BorderPane();//botones de accion
        
        //elemento para dar borde 
        VBox buttonsAndRegister = new VBox();
        buttonsAndRegister.setStyle("-fx-border-color:black;");
        buttonsAndRegister.setPadding(new Insets(10, 10, 10, 10));
        buttonsAndRegister.setSpacing(10);
        
        //Relleno de    challengersDatas     
        VBox characterInfoPly1= new VBox();//datos de cada jugador
        VBox characterInfoPly2= new VBox();
        //inicializa los personajes
        initCharacterInfo(characterInfoPly1,false);
        initCharacterInfo(characterInfoPly2,true);

        //ubica
        BorderPane locateCtInfo1 = new  BorderPane();
        BorderPane locateCtInfo2 = new  BorderPane();
        
        locateCtInfo1.setLeft(characterInfoPly1);
        locateCtInfo1.setRight(imgChrPly[1]);
        
        locateCtInfo2.setRight(characterInfoPly2);
        locateCtInfo2.setLeft(imgChrPly[0]);
        //añade en una fila
        challengersDatas.getChildren().add(locateCtInfo1);
        challengersDatas.getChildren().add(locateCtInfo2);

//relleno de buttonsPlayersAction
        //cuadro de botones
        GridPane buttonsPlayer1Format = new GridPane();
        GridPane buttonsPlayer2Format = new GridPane();       
        //botones de cada jugador
        ArrayList<Button> buttonsplayer1 = new ArrayList<>();
        ArrayList<Button> buttonsplayer2 = new ArrayList<>();
        //agrega los botones a los cuadros
        initBattleButtons(buttonsplayer1);
        initBattleButtons(buttonsplayer2);
        outButtonsBattle(buttonsPlayer1Format, buttonsplayer1);
        outButtonsBattle(buttonsPlayer2Format, buttonsplayer2);
        
        //pone en una linea
        buttonsPlayersAction.setRight(buttonsPlayer2Format);
        buttonsPlayersAction.setLeft(buttonsPlayer1Format);
        
        buttonsAndRegister.getChildren().add(buttonsPlayersAction);
        buttonsAndRegister.getChildren().add(this.registerView);

//añade a la pantalla
        view.getChildren().add(challengersDatas);        
        view.getChildren().add(buttonsAndRegister);
        
        return view;
    }
    
    //inicializa los botones
    private void initBattleButtons(ArrayList<Button> player) {
        //declaracion de los botones
        player.add(new Button("Ataque"));
        player.add(new Button("Ataque Final"));
        player.add(new Button("Defensa"));
        player.add(new Button("Curar"));

        //formato
        for (Button button : player) {
            button.setFont(Font.font("Verdana", 13));
            button.setMinSize(120, 27);
            button.setMaxSize(0, 27);
            //tamaño del boton buscar
        }
        
        //cuando clikea
        player.get(0).setOnAction(event -> {
            this.register.add("1");
            registerView();
        });
        player.get(1).setOnAction(event -> {
            this.register.add("2");
            registerView();
        });
        player.get(2).setOnAction(event -> {
            this.register.add("3");
            registerView();
        });
        player.get(3).setOnAction(event -> {
            this.register.add("4");
            registerView();
        });
        

    }
    
    //pone los botones en el recuadro
    private void outButtonsBattle(GridPane buttonsFormat, ArrayList<Button> buttonsplayer) {
        //formato
        buttonsFormat.setVgap(5);
        buttonsFormat.setHgap(5);
        buttonsFormat.setPadding(new Insets(5, 5, 5, 5));
        //pone en tabla
        buttonsFormat.add(buttonsplayer.get(0), 0, 0);
        buttonsFormat.add(buttonsplayer.get(1), 1, 0);
        buttonsFormat.add(buttonsplayer.get(2), 0, 1);
        buttonsFormat.add(buttonsplayer.get(3), 1, 1);

    }
    
    //inicializa los recuadros de los personajes
    private void initCharacterInfo(VBox boxCharacter,boolean pos){
        
        boxCharacter.setPadding(new Insets(10, 10, 10, 10));
        boxCharacter.setSpacing(1);
        boxCharacter.setStyle("-fx-border-color:black; -fx-border-width:3;");
        boxCharacter.setMaxSize(300, 120);
        boxCharacter.setMinSize(300, 120);

        GridPane panel = new GridPane();
        panel.setVgap(2);
        //etiquetas estaticas
        Label eNickName = new Label(a.get(2));
        Label eLife = new Label("Vida:");
        Label eStamite = new Label("Estamita:");
        Label nameUser = new Label(a.get(0) + " " + a.get(1));
        //ubicacion y control de vida y estamita
        int Hply=0;
        BorderPane position = new BorderPane();
        if(pos){
            nameUser.setAlignment(Pos.CENTER_RIGHT);
            eNickName.setAlignment(Pos.CENTER_RIGHT);
            boxCharacter.setAlignment(Pos.CENTER_RIGHT);
            Hply=1;
        }
        
        this.lifeCh[Hply] = new Label(a.get(3));
        this.lifeCh[Hply].setTextFill(javafx.scene.paint.Color.GREEN);
        this.lifeCh[Hply].setStyle("-fx-border-color:black;");
        this.stamiteCh[Hply] = new Label(a.get(4));
        this.stamiteCh[Hply].setTextFill(javafx.scene.paint.Color.GREEN);
        this.stamiteCh[Hply].setStyle("-fx-border-color:black;");
        //Cambia el formato de los label
        setStyler(nameUser, 20, 150, 25);
        setStyler(eNickName, 20, 150, 25);
        setStyler(eLife, 15, 80, 15);
        setStyler(eStamite, 15, 80, 15); 
        setStyler(this.stamiteCh[Hply], 15, 120, 20);
        setStyler(this.lifeCh[Hply], 15, 120, 20);
        //añade en su posicion
        panel.add(this.stamiteCh[Hply], 1, 1);
        panel.add(this.lifeCh[Hply], 1, 0);
        panel.add(eLife,0,0);
        panel.add(eStamite, 0,1);
        if(pos){
            position.setRight(panel);
        }else{
            position.setLeft(panel);
        }
        boxCharacter.getChildren().add(nameUser);
        boxCharacter.getChildren().add(eNickName);
        boxCharacter.getChildren().add(position);
        
    }

    private void setStyler(Label label, int sizeLetter, int weight, int tall) {
        //da formato a los label 
        label.setFont(Font.font("Verdana", sizeLetter));
        label.setMaxSize(weight, tall);
        label.setMinSize(weight, tall);
    }
    private void setImgCh(Label img, String dir){
        //formato de imagen
        ImageView image = new ImageView(new Image(dir));
        image.setFitHeight(150);
        img.setGraphic(image);
    }
    
    //actualizacion del registro de batalla
    private void registerView(){
        int sizeR = this.register.size();
        String outView = "";
        if(sizeR>4){
            for(int i=sizeR-4; i<(sizeR);i++){
                outView+=this.register.get(i)+"\n";
            }
        }
        else{
            for(int i=0; i<(sizeR);i++){
               outView+=this.register.get(i)+"\n"; 
            }
        }
        this.registerView.setText(outView);
    }

   


}
