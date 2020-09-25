
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.stage.Stage;

public class StartScene extends Application {

    public static void main(String[] args) {

        launch(StartScene.class);
    }

    @Override
    public void start(Stage window) throws Exception {

        ChooseCharactersScene startScreen = new ChooseCharactersScene();

        Registry registry = new Registry();

        FightWindow fight = new FightWindow();

        Statistics stast = new Statistics();

        startScreen.registerPlayerButton().setOnAction((event) -> {

            window.maxHeightProperty();
            window.setWidth(500);
            window.setHeight(500);
            window.setScene(registry.showView(window));
        });
        startScreen.getStartButton().setOnAction((event) -> {
            window.setWidth(860);
            window.setHeight(550);
            fight.setPlayers(startScreen.getPlayers());
            window.setScene(fight.showView());
        });

        registry.backStartScreenButton().setOnAction((event) -> {
            window.setWidth(620);
            window.setHeight(450);
            window.setScene(startScreen.showView());
        });
        fight.getNextView().setOnAction(event -> {
            window.setWidth(620);
            window.setHeight(450);
            window.setScene(stast.showView());
        });
        stast.getSeleccionPersonajes().setOnAction((event) -> {

            window.setWidth(500);
            window.setHeight(500);
            window.setScene(startScreen.showView());
        });

        stast.getSalir().setOnAction((event) -> {
            window.setWidth(620);
            window.setHeight(450);
            window.setScene(registry.showView(window));
        });

        window.setScene(startScreen.showView());
        window.show();

    }

}
