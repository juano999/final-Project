
import java.util.ArrayList;
import javafx.scene.image.Image;

public class Personaje {

    private String name;
    private int vida;
    private int estamina;
    private int ataque;
    private int ataqueFinal;
    private Image image;
    
    
    public Personaje() {

    }

    public void newCharacter(ArrayList<String> characters) {
        this.name = characters.get(0);
        this.vida = Integer.valueOf(characters.get(1));
        this.estamina =Integer.valueOf(characters.get(2));
        this.ataque =Integer.valueOf(characters.get(3));
        this.ataqueFinal = Integer.valueOf(characters.get(4));
    }
    
    
    
    

}
