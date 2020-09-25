
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Statistics {
    private BorderPane border;
    private GridPane gridpane;
    private TableView table;
     private Button salir;
     private    Button seleccionPersonajes;
   
    public Statistics(){
        this.border = new BorderPane();
        this.gridpane = new GridPane();
        this.table = new TableView();
        salir = new Button("Crear");
        seleccionPersonajes = new Button("Ir a seleccionar personajes");
    }

    public Button getSalir() {
        return salir;
    }

    public Button getSeleccionPersonajes() {
        return seleccionPersonajes;
    }
    
    public VBox table(){
        //Crear una tabla
        Registry registry = new Registry();
        ObservableList<Player> payerlist = registry.getPlayersList();
        Scene scene = new Scene(new Group());
        this.table.setEditable(true);
        //Columnas de la tabla 
        TableColumn usuarioCol = new TableColumn("Usuario: ");
        usuarioCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("usuario"));
        TableColumn victoriasCol = new TableColumn("Victorias: ");
        victoriasCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("victories"));
        this.table.getColumns().add(usuarioCol);
        this.table.getColumns().add(victoriasCol);
         table.setItems(payerlist);
 
        VBox vbox = new VBox();
        vbox.setSpacing(100);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().add(table);
        vbox.maxWidth(25);
        vbox.maxHeight(25);
        return vbox;
    }
    public Scene showView(){
        
        salir.setTranslateX(220);
        seleccionPersonajes.setTranslateX(170);
        Label play = new Label("POO");
        Label label2 = new Label ("Values");
play.setFont(new Font("Cambria", 32));
play.setTranslateX(220);
        Label result = new Label("BATTLEPRO");
        result.setFont(new Font("Cambria", 32));
result.setTranslateX(170);
        VBox hbox = new VBox();
        hbox.getChildren().addAll(play, result, table(), salir, seleccionPersonajes);
        hbox.setStyle("-fx-border-color:black; -fx-border-width: 5;");
        this.border.setCenter(hbox);
        this.border.setStyle("-fx-border-color:black; -fx-border-width: 1;");
        Scene registro = new Scene(this.border);
        return registro;
    }
}
