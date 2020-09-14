 // @author carl-

import java.util.ArrayList;
import javafx.application.Application;

import javafx.stage.Stage;

public class StartScene extends Application {

    public static void main(String[] args) {

        launch(StartScene.class);
    }

    @Override
    public void start(Stage window) throws Exception {

        ChooseCharactersScene startScreen = new ChooseCharactersScene();

        Registry registry = new Registry();

        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> personaje1 = new ArrayList<>();
        ArrayList<String> personaje2 = new ArrayList<>();
        personaje1.add("1");
        personaje1.add("200");
        personaje1.add("500");
        personaje1.add("70");
        personaje1.add("80");
        personaje1.add("20");
        personaje1.add("elementalAgua.jpg");
        personaje2.add("2");
        personaje2.add("210");
        personaje2.add("300");
        personaje2.add("50");
        personaje2.add("100");
        personaje2.add("10");
        personaje2.add("elementalFuego.jpg");
        Player p1 = new Player("A", "lA", "1752", "Q1w");
        p1.newCaracter(personaje1);
        Player p2 = new Player("B", "lB", "1752", "Q1w");
        p2.newCaracter(personaje2);
        players.add(p1);
        players.add(p2);
        FightWindow fight = new FightWindow(players);
        
        Statistics stast = new Statistics();

        startScreen.registerPlayerButton().setOnAction((event) -> {

            window.maxHeightProperty();
            window.setWidth(500);
            window.setHeight(500);
            window.setScene(registry.showView());
        });

        registry.backStartScreenButton().setOnAction((event) -> {
            window.setWidth(620);
            window.setHeight(450);
            window.setScene(startScreen.showView());
        });

        window.setScene(fight.showView());
        window.show();

    }

}
