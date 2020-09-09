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
        FightWindow fight = new FightWindow(players);
        
        Statistics stast = new Statistics();

        startScreen.registerPlayerButton().setOnAction((event) -> {

            window.maxHeightProperty();
            window.setWidth(500);
            window.setHeight(500);
            window.setScene(registry.showView(window));
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
