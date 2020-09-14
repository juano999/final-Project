
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
    private HBox viewT;
    private Scene viewF;
    private boolean turn = false;
    private ArrayList<Player> players;
    private ArrayList<Button> buttonsplayer1 = new ArrayList<>();
    private ArrayList<Button> buttonsplayer2 = new ArrayList<>();
    private FightCaracters fight;
    private int optionF;
    private Statistics statics;

    public FightWindow(ArrayList<Player> players) {
        this.players = players;
        this.viewT = new HBox();
        this.viewT.getChildren().add(new Label(""));
        this.register = new ArrayList<>();
        this.lifeCh = new Label[2];
        this.stamiteCh = new Label[2];
        this.imgChrPly = new Label[2];
        this.imgChrPly[0] = new Label();
        this.imgChrPly[0].setGraphic(this.players.get(0).getPersonaje().getImage());
        this.imgChrPly[1] = new Label();
        this.imgChrPly[1].setGraphic(this.players.get(1).getPersonaje().getImage());
        this.registerView = new Label();
        this.registerView.setStyle("-fx-border-color:black;");
        this.registerView.setAlignment(Pos.TOP_LEFT);
        setStyler(this.registerView, 10, 780, 50);
//        setImgCh(imgChrPly[0], "lol.jpg");
//        setImgCh(imgChrPly[1], "lol.jpg");
        fight = new FightCaracters(players);
        this.optionF = 0;
        this.statics = new Statistics();

    }

    public Scene showView() {
        this.viewT.getChildren().set(0, getView());
        viewF = new Scene(viewT, 860, 500);
        return viewF;
    }

    public Parent getView() {

        VBox view = new VBox();//la pantalla completa
        //formato de pantalla
        view.setSpacing(5);
        view.setStyle("-fx-border-color:black;");
        view.setPadding(new Insets(10, 10, 10, 10));
        //elementos dentro de la pantalla

        VBox challengersDatas = new VBox();//datos del jugadores y personaje
        BorderPane buttonsPlayersAction = new BorderPane();//botones de accion

        //elemento para dar borde 
        VBox buttonsAndRegister = new VBox();
        buttonsAndRegister.setStyle("-fx-border-color:black;");
        buttonsAndRegister.setPadding(new Insets(10, 10, 10, 10));
        buttonsAndRegister.setSpacing(10);

        //Relleno de    challengersDatas     
        VBox characterInfoPly1 = new VBox();//datos de cada jugador
        VBox characterInfoPly2 = new VBox();
        //inicializa los personajes
        initCharacterInfo(characterInfoPly1, false);
        initCharacterInfo(characterInfoPly2, true);

        //ubica
        BorderPane locateCtInfo1 = new BorderPane();
        BorderPane locateCtInfo2 = new BorderPane();

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
        //agrega los botones a los cuadros

        initBattleButtons(buttonsplayer1, true);
        initBattleButtons(buttonsplayer2, false);
        buttonsPlayerDisable();
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
    private void initBattleButtons(ArrayList<Button> player, boolean howPlayer) {
        //declaracion de los botones
        player.add(new Button("Ataque"));
        player.add(new Button("Ataque Final"));
        player.add(new Button("Curar"));
        player.add(new Button("Defensa"));

        //formato
        for (Button button : player) {
            button.setFont(Font.font("Verdana", 13));
            button.setMinSize(120, 27);
            button.setMaxSize(0, 27);
            //tamaño del boton buscar
        }

        //cuando clikea
        player.get(0).setOnAction(event -> {

            fight.atack(howPlayer);

            if (stamiteIsEmpty(howPlayer, 5)) {
                actuallyView(" uso Ataque");
            } else {
                actuallyView(" uso Ataque pero reduce su vida");
            }

        });
        player.get(1).setOnAction(event -> {

            fight.atackFinal(howPlayer);

            actuallyView(" uso Ataque Final");

        });
        player.get(2).setOnAction(event -> {

            fight.heal(howPlayer);

            actuallyView(" uso Curar");

        });
        player.get(3).setOnAction(event -> {
            fight.protect(howPlayer);
            actuallyView(" uso Defensa");

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
    private void initCharacterInfo(VBox boxCharacter, boolean pos) {
        Label nameUser = new Label("");
        Label eNickName = new Label("");
        int Hply = 0;
        if (pos) {
            nameUser.setAlignment(Pos.CENTER_RIGHT);
            eNickName.setAlignment(Pos.CENTER_RIGHT);
            boxCharacter.setAlignment(Pos.CENTER_RIGHT);
            Hply = 1;
        }
        boxCharacter.setPadding(new Insets(10, 10, 10, 10));
        boxCharacter.setSpacing(1);
        boxCharacter.setStyle("-fx-border-color:black; -fx-border-width:3;");
        boxCharacter.setMaxSize(300, 120);
        boxCharacter.setMinSize(300, 120);

        GridPane panel = new GridPane();
        panel.setVgap(2);
        //etiquetas estaticas
        eNickName = new Label(this.players.get(Hply).getPersonaje().getName());
        Label eLife = new Label("Vida:");
        Label eStamite = new Label("Estamita:");
        nameUser = new Label(this.players.get(Hply).nameUser());
        //ubicacion y control de vida y estamita

        BorderPane position = new BorderPane();

        resetLabels(Hply);
        this.stamiteCh[Hply].setTextFill(javafx.scene.paint.Color.GREEN);
        this.lifeCh[Hply].setTextFill(javafx.scene.paint.Color.GREEN);
        this.lifeCh[Hply].setStyle("-fx-border-color:black;");
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
        panel.add(eLife, 0, 0);
        panel.add(eStamite, 0, 1);
        if (pos) {
            position.setRight(panel);
        } else {
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

    private void setImgCh(Label img, String dir) {
        //formato de imagen
        ImageView image = new ImageView(new Image(dir));
        image.setFitHeight(150);
        img.setGraphic(image);
    }

    //actualizacion del registro de batalla
    private void registerView() {
        int sizeR = this.register.size();
        String outView = "";
        if (sizeR > 4) {
            for (int i = sizeR - 4; i < (sizeR); i++) {
                outView += this.register.get(i) + "\n";
            }
        } else {
            for (int i = 0; i < (sizeR); i++) {
                outView += this.register.get(i) + "\n";
            }
        }
        this.registerView.setText(outView);
    }

    //inicializa los labels de las caracteristicas de los jugadores
    private void resetLabels(int Hply) {
        this.lifeCh[Hply] = new Label(this.players.get(Hply).getPersonaje().getVida() + "/" + this.players.get(Hply).getLife());

        this.stamiteCh[Hply] = new Label(this.players.get(Hply).getPersonaje().getEstamina() + "/" + this.players.get(Hply).getStamite());
    }

    //devuelve que jugador esta ejecutando la accion
    private String howPlayer(boolean how) {
        String hplay = "Jugador ";
        if (!how) {
            hplay += "1:";
        } else {
            hplay += "2:";
        }
        return hplay;
    }
    //devuelve si aun le queda estamita al jugador para la accion

    private boolean stamiteIsEmpty(boolean howPlayer, int cost) {
        int t = 0;
        if (!howPlayer) {
            t = 1;
        }
        return (this.players.get(t).stamiteIsNotEmpty(cost));
    }
    //desactiva los botones si no hay estamita

    private void buttonActionDisable(ArrayList<Button> player, boolean howPlayer) {
        if (!stamiteIsEmpty(!howPlayer, 50)) {
            player.get(1).setDisable(true);
        }
        if (!stamiteIsEmpty(!howPlayer, 20)) {
            player.get(2).setDisable(true);
        }
        if (!stamiteIsEmpty(!howPlayer, 75)) {
            player.get(3).setDisable(true);
        }
    }
//devuelve los botones de un jugador

    private ArrayList<Button> buttonsPlayer(boolean howPlayer) {
        if (howPlayer) {
            return this.buttonsplayer2;
        } else {
            return this.buttonsplayer1;
        }
    }
//desactiva o activa segun corresponda el caso

    private void buttonsPlayerDisable() {
        int turnH = 0, other = 1;
        if (turn) {
            turnH = 1;
            other = 0;
        }
        if (this.players.get(turnH).getPersonaje().getVida() == 0 && this.players.get(other).getPersonaje().getVida() == 0) {
            this.register.add("empate");
            this.optionF = 2;
            for (Button button : buttonsPlayer(!turn)) {
                button.setDisable(true);
            }
            for (Button button : buttonsPlayer(turn)) {
                button.setDisable(true);
            }
        }
        if (this.players.get(turnH).getPersonaje().getVida() == 0) {
            this.optionF = 1;
            this.players.get(other).win();
            this.register.add(howPlayer(!turn) + " gano");
            for (Button button : buttonsPlayer(!turn)) {
                button.setDisable(true);
            }
            for (Button button : buttonsPlayer(turn)) {
                button.setDisable(true);
            }
        } else {
            for (Button button : buttonsPlayer(!turn)) {
                button.setDisable(true);
            }
            for (Button button : buttonsPlayer(turn)) {
                button.setDisable(false);
            }
            buttonActionDisable(buttonsPlayer(turn), turn);
        }

    }
//actualiza la ventana de estados

    private void actuallyView(String dialog) {
        this.lifeCh = fight.resetLabelLife(lifeCh);
        this.stamiteCh = fight.resetLabelStamite(stamiteCh);
        this.register.add(howPlayer(turn) + dialog);

        this.turn = !this.turn;
        buttonsPlayerDisable();
        registerView();
        finalation();

    }
//finaliza el juego

    private void finalation() {
        switch (this.optionF) {
            case 1:
                //finalizo con ganador
                viewF = this.statics.showView();
//aun toca ver como pasar a la otra ventana
                break;
            case 2:
                //empate
                Button again = new Button("EMPATE\nDe nuevo");
                again.setOnAction(event -> {
                    this.viewT.getChildren().remove(1);
                    this.players.get(0).resetP();
                    this.players.get(1).resetP();
                    this.optionF = 0;
                });
                this.viewT.getChildren().add(again);
                break;
        }
    }

}
