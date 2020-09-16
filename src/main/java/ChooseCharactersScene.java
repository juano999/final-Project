
import java.util.ArrayList;
import java.util.List;
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



    public ChooseCharactersScene() {
        this.registerPlayerButton = new Button("Registrar \nJugador");
        this.startButton = new Button("Empezar");
    }
    public Button getStartButton() {
        return startButton;
    }

    public Button registerPlayerButton() {
        return registerPlayerButton;
    }
    
    
    
    public Scene showView() {
        Button selectPlayer1Button = new Button("Seleccionar \nJugador 1");
        Button selectPlayer2Button = new Button("Seleccionar \nJugador 2");
        
        
        startButton.setAlignment(Pos.CENTER);
        VBox bottomButtons = new VBox();
        bottomButtons.getChildren().addAll(startButton, registerPlayerButton);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setSpacing(10);

        List<String> charactersList = new ArrayList<>();
        charactersList.add("personaje Fuego");
        charactersList.add("personaje Agua");
        charactersList.add("personaje Aire");
        charactersList.add("personaje Tierra");
        ObservableList<String> characters = FXCollections.observableList(charactersList);
        ChoiceBox menuCharacters = new ChoiceBox(characters);
        menuCharacters.setPrefWidth(200);

        Label gameTitleText = new Label("Dragons");
        gameTitleText.setPadding(new Insets(0, 0, 0, 14));
        gameTitleText.setFont(Font.font("Showcard Gothic", 35));

        Label charactersText = new Label("Personajes:");
        charactersText.setPadding(new Insets(0, 0, 0, 33));
        charactersText.setFont(Font.font("Showcard Gothic", 20));

        List<String> namesList = new ArrayList<>();
        namesList.add("Juan");
        namesList.add("Karen");
        namesList.add("Chris");
        ObservableList<String> names = FXCollections.observableList(namesList);

        ChoiceBox menuPlayers1 = new ChoiceBox(names);
        ChoiceBox menuPlayers2 = new ChoiceBox(names);
        menuPlayers1.setPrefWidth(150);
        menuPlayers2.setPrefWidth(150);
        
        Image img1 = new Image("dragon_fuego.jpg");       
        ImageView img1View = new ImageView(img1);
        img1View.setFitWidth(100);
        ImageView img2View = new ImageView(img1);
        img2View.setFitWidth(100);
        Image imgCharacter1 = new Image("dragon_fuego.jpg");
        ImageView imgCharacter1View = new ImageView(imgCharacter1);
        imgCharacter1View.setFitWidth(200);
        
        BorderPane selectCharSpace = new BorderPane();
               
        Image imgFire = new Image("dragon_fuego.jpg");
        Image imgWater = new Image("agua.jpeg");
        Image imgAir = new Image("aire.jpeg");
        Image imgWorst = new Image("papa.jpeg");
        ImageView charFireView = new ImageView(imgFire);
        ImageView charWaterView = new ImageView(imgWater);
        ImageView charAirView = new ImageView(imgAir);
        ImageView charWorstView = new ImageView(imgWorst);
        
        ArrayList<ImageView> images = new ArrayList<>();
        images.add(charFireView);
        images.add(charWaterView);
        images.add(charAirView);
        images.add(charWorstView);
        for(ImageView view: images) {
            view.setFitHeight(200);
            view.setFitWidth(200);
        }
        
        menuCharacters.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldSelected, Object newSelected) {
                System.out.println(newSelected);
                selectCharSpace.setCenter(images.get((int)newSelected));
                selectPlayer1Button.setOnAction((event) -> {
                   img1View.setImage(images.get((int)newSelected).getImage());
                   img1View.setFitHeight(100);
                   img1View.setFitWidth(100);
                });
                selectPlayer2Button.setOnAction((event) -> {
                   img2View.setImage(images.get((int)newSelected).getImage());
                   img2View.setFitHeight(100);
                   img2View.setFitWidth(100);
                });
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
    
}
