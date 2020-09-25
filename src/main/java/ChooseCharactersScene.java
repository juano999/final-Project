
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

 // @author carl-

 
public class ChooseCharactersScene {
    private Button registerPlayerButton;
    private Button startButton;
    private Player[] players;
    private ArrayList<String> playersRegister;
    private ArrayList<String> playersNames;
    private boolean selection1 = false;
    private boolean selection2 = false;
    



    public ChooseCharactersScene() {
        this.playersNames= new ArrayList<>();
        this.playersRegister= new ArrayList<>();
        this.registerPlayerButton = new Button("Registrar \nJugador");
        this.startButton = new Button("Empezar");
        this.players= new Player[2];
        try (Scanner scan = new Scanner(Paths.get("usuarios.txt"))) {
            while (scan.hasNextLine()) {
                String string = scan.nextLine();
                if (string.equals(" ")) {
                    break;
                }
                this.playersRegister.add(string);
                String[] parts = string.split(" ");
                this.playersNames.add(parts[1]);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public Button getStartButton() {
        return startButton;
    }

    public Button registerPlayerButton() {
        return registerPlayerButton;
    }
    //faltan
    //cargar personas desde el archivo
    
    
    public Scene showView() {
        this.startButton.setDisable(true);

        CharacterInfo cInfo1= new CharacterInfo();
        CharacterInfo cInfo2= new CharacterInfo();
        cInfo1.resetCharacter(0);
        cInfo2.resetCharacter(0);
        Button selectPlayer1Button = new Button("Seleccionar \nJugador 1");
        Button selectPlayer2Button = new Button("Seleccionar \nJugador 2");
        selectPlayer2Button.setDisable(true);       
        selectPlayer1Button.setDisable(true);       
        startButton.setAlignment(Pos.CENTER);
        VBox bottomButtons = new VBox();
        bottomButtons.getChildren().addAll(startButton, registerPlayerButton);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setSpacing(10);

//para el combo central
        List<String> charactersList = cInfo1.giveNames();
        ObservableList<String> characters = FXCollections.observableList(charactersList);
        ChoiceBox menuCharacters = new ChoiceBox(characters);
        menuCharacters.setPrefWidth(200);
//titulos
        Label gameTitleText = new Label("Dragons");
        gameTitleText.setPadding(new Insets(0, 0, 0, 14));
        gameTitleText.setFont(Font.font("Showcard Gothic", 35));

        Label charactersText = new Label("Personajes:");
        charactersText.setPadding(new Insets(0, 0, 0, 33));
        charactersText.setFont(Font.font("Showcard Gothic", 20));
//combo de seleccion nombres
        List<String> namesList = this.playersNames;
        ObservableList<String> names = FXCollections.observableList(namesList);

        ChoiceBox menuPlayers1 = new ChoiceBox(names);
        ChoiceBox menuPlayers2 = new ChoiceBox(names);
        menuPlayers1.setPrefWidth(150);
        menuPlayers2.setPrefWidth(150);
        menuPlayers2.setDisable(true);

  //imagenes       

        ImageView img1View = cInfo1.giveImg();
        ImageView img2View = cInfo2.giveImg();
        img1View.setFitWidth(150);
        img2View.setFitWidth(150);
        Image imgCharacter1 = new Image("dragon_fuego.jpg");
        ImageView imgCharacter1View = new ImageView(imgCharacter1);
        imgCharacter1View.setFitWidth(200);
        
        BorderPane selectCharSpace = new BorderPane();

   //insertar imagenes     
        ArrayList<ImageView> images = cInfo1.giveImage();

        for(ImageView view: images) {//da formato
            view.setFitHeight(200);
            view.setFitWidth(200);
        }
        
        menuCharacters.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldSelected, Object newSelected) {
                System.out.println(newSelected);
                selectCharSpace.setCenter(images.get((int)newSelected+1));
                selectPlayer1Button.setOnAction((event) -> {
                   img1View.setImage(images.get((int)newSelected+1).getImage());
                   img1View.setFitHeight(100);
                   img1View.setFitWidth(100);
                   cInfo1.resetCharacter((int)newSelected+1);
                   players[0].newCaracter(cInfo1.getCharacter());
                    menuPlayers2.setDisable(false);
                    selectPlayer2Button.setDisable(false);
                });
                selectPlayer2Button.setOnAction((event) -> {
                   img2View.setImage(images.get((int)newSelected+1).getImage());
                   img2View.setFitHeight(100);
                   img2View.setFitWidth(100);
                    cInfo2.resetCharacter((int) newSelected + 1);
                    players[1].newCaracter(cInfo2.getCharacter());
                    startButton.setDisable(false);
                });
            }
        });
        menuPlayers1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldSelected, Object newSelected) {
                System.out.println(newSelected);
                String[] parts = playersRegister.get((int)newSelected).split(" ");
                players[0]= new Player(parts[1],parts[2],parts[3],parts[0],parts[4],Integer.valueOf(parts[5]));
                selectPlayer1Button.setDisable(false);  
            }
        });
        menuPlayers2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldSelected, Object newSelected) {
                System.out.println(newSelected);
                String[] parts = playersRegister.get((int)newSelected).split(" ");
                players[1]= new Player(parts[1],parts[2],parts[3],parts[0],parts[4],Integer.valueOf(parts[5]));
            }
        });
        


        VBox componentsLeft = new VBox();
        componentsLeft.setSpacing(10);
        componentsLeft.setPadding(new Insets(20, 20, 20, 20));
        componentsLeft.getChildren().addAll(menuPlayers1, img1View, selectPlayer1Button);

        VBox componentsCenter = new VBox();
        componentsCenter.setSpacing(10);
        componentsCenter.setPadding(new Insets(20, 20, 20, 20));
        componentsCenter.getChildren().addAll(gameTitleText, charactersText, menuCharacters, selectCharSpace, bottomButtons);

        VBox componentsRight = new VBox();
        componentsRight.setSpacing(10);
        componentsRight.setPadding(new Insets(20, 20, 20, 20));
        componentsRight.getChildren().addAll(menuPlayers2, img2View, selectPlayer2Button);

        BorderPane layout = new BorderPane();

        layout.setLeft(componentsLeft);
        layout.setRight(componentsRight);
        layout.setCenter(componentsCenter);
        
        Scene startScreen = new Scene(layout, 620, 430);
        
        return startScreen;
    }

    public Player[] getPlayers() {
        return players;
    }
    
}
