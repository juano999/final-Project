
    
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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

    private ObservableList<Player> playersList;
    private ObservableList<String> ids;
    private ArrayList<String> names;
    private ArrayList<String> lastNames;
    private ArrayList<String> cedules;
    private ArrayList<String> usuarios;
    private Label error;

    public Registry() {
        this.playersList = FXCollections.observableArrayList();
        this.ids = FXCollections.observableArrayList();
        this.names = new ArrayList<>();
        this.cedules = new ArrayList<>();
        this.lastNames = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.error = new Label("");
        try (Scanner scan = new Scanner(Paths.get("usuarios.txt"))) {
            while (scan.hasNextLine()) {
                String string = scan.nextLine();
                if (string.equals(" ")) {
                    break;
                }
                String[] parts = string.split(" ");
                String id = parts[0];
                this.ids.add(id);
                String name = parts[1];
                this.names.add(name);
                String lastName = parts[2];
                this.lastNames.add(lastName);
                String ced = parts[3];
                this.cedules.add(ced);
                String usuario = parts[4];
                this.usuarios.add(usuario);
                int wins = Integer.valueOf(parts[5]);
                this.playersList.add(new Player(name, lastName, id, ced, usuario, wins));

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        this.seleccionarPersonajes = new Button("Ir a seleccionar personajes");
    }

    public Scene showView(Stage window) {

        BorderPane border = new BorderPane();
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
        Label newId = new Label("Nuevo Id: ");
        
          TextField newIdText = new TextField();
        TextField nombreText = new TextField();
        TextField apellidoText = new TextField();
        TextField cedulaText = new TextField();
        TextField usuarioText = new TextField();
        ArrayList<String> na = this.names;
        ArrayList<String> lastna = this.lastNames;
        ArrayList<String> cedu = this.cedules;
        ArrayList<String> usua = this.usuarios;
        ObservableList<String> idd = this.ids;
 
        id.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov,
                Number value, Number nuewvalue) {
                nombreText.setText(na.get(nuewvalue.intValue()));
                apellidoText.setText(lastna.get(nuewvalue.intValue()));
                cedulaText.setText(cedu.get(nuewvalue.intValue()));
                usuarioText.setText(usua.get(nuewvalue.intValue()));
            }
        }
        );
        gridpane.add(id, 2, 0);
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
        gridpane.add(this.error, 2, 6);
        gridpane.add(crear, 3, 5);
        gridpane.add(newId, 1, 1);
        gridpane.add(newIdText, 2, 1);
        gridpane.setStyle("-fx-border-color:black; -fx-border-width: 1;");
        border.setTop(gridpane);

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
                new PropertyValueFactory<Player, String>("cedula"));
        TableColumn usuarioCol = new TableColumn("Usuario: ");
        usuarioCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("usuario"));
        TableColumn idCol = new TableColumn("id: ");
        idCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("id"));
        TableColumn victoriasCol = new TableColumn("Victorias: ");
        victoriasCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("victories"));
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
        modificar.setOnAction((event) -> {
            setPlayersList(String.valueOf(nombreText.getText()), String.valueOf(apellidoText.getText()), String.valueOf(123), String.valueOf(cedulaText.getText()), String.valueOf(usuarioText.getText()));
            safeInformation();
        });
        crear.setOnAction((event) -> {
            addPlayer(String.valueOf(nombreText.getText()), String.valueOf(apellidoText.getText()), String.valueOf(newIdText.getText()), String.valueOf(cedulaText.getText()), String.valueOf(usuarioText.getText()));
            safeInformation();
        });
        eliminar.setOnAction((event) -> {
            removePlayer(String.valueOf(usuarioText.getText()));
            safeInformation();
        });

        Scene registro = new Scene(border);
        return registro;
    }

    public ArrayList<String> getUsuarios() {
        ArrayList<String> newUsuarios = new ArrayList<>();
        for(Player person : this.playersList){
            newUsuarios.add(person.getUsuario());
        }
        return newUsuarios;
    }

    public void setPlayersList(String name, String lastName, String id, String ced, String usuario) {
        for (Player play : this.playersList) {
            if (play.getId().equals(id)) {
                play.setCedula(ced);
                play.setName(name);
                play.setLastName(lastName);
                play.setUsuario(usuario);
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

    public void removePlayer(String usuario) {
        ObservableList<Player> playersLis = FXCollections.observableArrayList();
        ObservableList<String> ides= FXCollections.observableArrayList();
        
        for (int i= this.playersList.size()-1; i>=0; i-- ) {
            if (!usuario.equals(this.playersList.get(i).getUsuario())) {
                playersLis.add(this.playersList.get(i));
                ides.add(this.playersList.get(i).getId());
            }
        };
        this.ids = ides;
        this.playersList = playersLis;
    }

    public void addPlayer(String name, String lastName, String id, String ced, String usuario) {
        
        if(this.ids.contains(id)){
            this.error.setText("Usuario ya exite ingrese otro dato");
            
        }else{
             this.playersList.add(new Player(name, lastName, id, ced, usuario, 0));
            this.ids.add(id);
            this.usuarios.add(usuario);
        }
    }

    public void safeInformation() {
        File f;
        f = new File("usuarios.txt");


        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw); 
            for(Player play: this.playersList){
                wr.write(play.getId() + " " + play.getName() + " " + play.getLastName() + " " + play.getCedula() + " " + play.getUsuario() + " " + play.getVictories() + "\n");
            }
            wr.close();
            bw.close();


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void win(String name){
        for(int i=0; i<this.playersList.size(); i++){
            if(this.playersList.get(i).getName().equals(name)){
                int wins = this.playersList.get(i).getVictories();
                this.playersList.get(i).setVictories(wins+1);
            }
        }
        safeInformation();
    }
}


    


