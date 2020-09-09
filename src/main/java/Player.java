
import java.util.ArrayList;


public class Player {
    private String name;
    private String lastName;
    private String cedula;
    private String id;
    private String victories;
    private Personaje personaje;
    private final ArrayList<String> personaje1 = new ArrayList<>();
    private final ArrayList<String> personaje2 = new ArrayList<>();
    private final ArrayList<String> personaje3 = new ArrayList<>();
    private final ArrayList<String> personaje4 = new ArrayList<>();
    
    public Player(String name, String lastName, String cedula, String id, String victorias){
        this.name = name;
        this.lastName = lastName;
        this.cedula = cedula;
        this.id = id;
        personaje1.add("");
        personaje1.add("");
        personaje1.add("");
        personaje1.add("");
        personaje1.add("");
        personaje2.add("");
        personaje2.add("");
        personaje2.add("");
        personaje2.add("");
        personaje3.add("");
        personaje3.add("");
        personaje3.add("");
        personaje3.add("");
        personaje4.add("");
        personaje4.add("");
        personaje4.add("");
        personaje4.add("");
        personaje4.add("");
    }
    
    public ArrayList<String> character(String name){
        if(name.equals(this.personaje1.get(0))){
            return this.personaje1;
        }
        if(name.equals(this.personaje2.get(0))){
            return this.personaje1;
        }
        if(name.equals(this.personaje3.get(0))){
            return this.personaje1;
        }
        if(name.equals(this.personaje4.get(0))){
            return this.personaje1;
        }
        return null;
    }
    
    public String getName(){
        return this.name;
    }
    public String getLastName(){
        return this.lastName;
    }
    public  String  getCedula(){
        return this.cedula;
    }
    public  String  getId(){
        return this.id;
    }
}
