
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Registry {

    private Button seleccionarPersonajes;
    
    private  ObservableList<Player> playersList;
    private  ObservableList<String> ids;
   
    public Registry(){
        this.playersList = FXCollections.observableArrayList();
        this.ids =FXCollections.observableArrayList();
        try (Scanner scan = new Scanner(Paths.get("usuarios.txt"))){
            while(scan.hasNextLine()){
                String string = scan.nextLine();
                if(string.equals(" ")){
                    break;
                }
                String[] parts = string.split(" ");
                String id = parts[0];
                ids.add(id);
                String name = parts[1];
                String lastName = parts[2];
                String ced = parts[3];
                String usuario = parts[4];
                int wins = Integer.valueOf(parts[5]);
                this.playersList.add(new Player(name, lastName, id, ced,usuario, wins));
                
            }
        }catch(Exception e){
               System.out.println("Error: " + e.getMessage()); 
        }

        this.seleccionarPersonajes = new Button("Ir a seleccionar personajes");
    }
    

    public Scene showView(Stage window){
        
        BorderPane border  = new BorderPane();
        GridPane gridpane = new GridPane();
        TableView table = new TableView();
        ChoiceBox id = new ChoiceBox(this.ids);
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
        nombreCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("name"));
        TableColumn apellidoCol = new TableColumn("Apellido: ");
        apellidoCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("lastName"));
        TableColumn cedulaCol = new TableColumn("Cedula: ");
        cedulaCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("ced"));
        TableColumn usuarioCol = new TableColumn("Usuario: ");
        usuarioCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("usuario"));
        TableColumn idCol = new TableColumn("id: ");
        idCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("id"));
        TableColumn victoriasCol = new TableColumn("Victorias: ");
       victoriasCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("wins"));
        table.getColumns().add(idCol);
        table.getColumns().add(nombreCol);
        table.getColumns().add(apellidoCol);
        table.getColumns().add(cedulaCol);
        table.getColumns().add(usuarioCol);
        table.getColumns().add(victoriasCol);
        table.setItems(this.playersList);
 
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

    public void setPlayersList(String name, String lastName, String id, String ced,String usuario, int wins) {
        for(Player play:this.playersList){
            if(play.getId().equals(id)){
                play.setCed(ced);
                play.setName(name);
                play.setLastName(lastName);
                play.setUsuario(usuario);
                play.setWins(wins);
            }
        }
        this.playersList = playersList;
    }

    public Button backStartScreenButton() {
        return seleccionarPersonajes;
    }
    
    

    public ObservableList<Player> getPlayersList() {
        return playersList;
    }
    
    public void addPlayer(Player player) {
        this.playersList.add(player);
        
    }
}
