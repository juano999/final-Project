
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Registry {

    private Button seleccionarPersonajes;
    private ArrayList<Player> playersList;
   
    public Registry(){

        this.seleccionarPersonajes = new Button("Ir a seleccionar personajes");
    }
    

    public Scene showView(){
        
        BorderPane border  = new BorderPane();
        GridPane gridpane = new GridPane();
        TableView table = new TableView();
        ChoiceBox id = new ChoiceBox(FXCollections.observableArrayList(
                "First", "Second", "Third")
        );
        Button modificar = new Button("Modificar");
        Button eliminar = new Button("Eliminar");
        Button crear = new Button("Crear");
       // Button seleccionarPersonajes = new Button("Ir a seleccionar personajes");
        
        Label nombre = new Label("Nombre: ");
        Label apellido = new Label("Apellido: ");
        Label cedula = new Label("Cedula: ");
        Label usuario = new Label("Usuario: ");
        TextField nombreText = new TextField();
        TextField apellidoText = new TextField();
        TextField cedulaText = new TextField();
        TextField usuarioText = new TextField();
        gridpane.add(id, 2, 1);
        gridpane.add(nombre, 1, 2);
        gridpane.add(nombreText, 2, 2);
        gridpane.add(modificar, 3, 2);
        gridpane.add(apellido, 1, 3);
        gridpane.add(apellidoText, 2, 3);
        gridpane.add(eliminar, 3, 3);
        gridpane.add(cedula, 1, 4);
        gridpane.add(cedulaText, 2, 4);
        gridpane.add(usuario, 1, 5);
        gridpane.add(usuarioText, 2, 5);
        gridpane.add(crear, 3, 5);
        gridpane.setStyle("-fx-border-color:black; -fx-border-width: 1;");
        border.setTop(gridpane);
        Scene scene = new Scene(new Group());
        table.setEditable(true);
        //Columnas de la tabla 
        TableColumn nombreCol = new TableColumn("Nombre: ");
        TableColumn apellidoCol = new TableColumn("Apellido: ");
        TableColumn cedulaCol = new TableColumn("Cedula: ");
        TableColumn usuarioCol = new TableColumn("Usuario: ");
        TableColumn idCol = new TableColumn("id: ");
        TableColumn victoriasCol = new TableColumn("Victorias: ");
        table.getColumns().add(idCol);
        table.getColumns().add(nombreCol);
        table.getColumns().add(apellidoCol);
        table.getColumns().add(cedulaCol);
        table.getColumns().add(usuarioCol);
        table.getColumns().add(victoriasCol);
 
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().add(table);
        vbox.maxWidth(100);
        vbox.maxHeight(100);
        border.setCenter(vbox);
        border.setBottom(seleccionarPersonajes);
        border.setStyle("-fx-border-color:black; -fx-border-width: 1;");
            
        Scene registro = new Scene(border);
        return registro;
    }

    public Button backStartScreenButton() {
        return seleccionarPersonajes;
    }
    
    

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }
    
    public void addPlayer(Player player) {
        this.playersList.add(player);
        
    }
}
