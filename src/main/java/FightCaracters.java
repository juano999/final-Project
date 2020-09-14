
import java.util.ArrayList;
import javafx.scene.control.Label;

public class FightCaracters {

    private ArrayList<Player> players;

    public FightCaracters(ArrayList<Player> players) {

        this.players = players;
    }
//ejecuta el ataque 

    public void atack(boolean whoPlayer) {
        int turn = 0, other = 1;
        if (!whoPlayer) {
            turn = 1;
            other = 0;
        }
        if (this.players.get(turn).stamiteIsNotEmpty(5)) {
            System.out.println("si hay mana");
            this.players.get(other).damage(this.players.get(turn).atackAction());
        } else {
            int damgeT = this.players.get(turn).atackAction();
            this.players.get(other).damage(damgeT);
            this.players.get(turn).damage(damgeT);
            this.players.get(turn).getPersonaje().stamiteStatic();
        }
        System.out.println(turn + " uso ataque " + this.players.get(turn).getPersonaje().getEstamina());

    }
    //ejecuta el ataque fuerte

    public void atackFinal(boolean whoPlayer) {
        int turn = 0, other = 1;
        if (!whoPlayer) {
            turn = 1;
            other = 0;
        }

        if (this.players.get(turn).stamiteIsNotEmpty(50)) {
            System.out.println("si hay mana");
            this.players.get(other).damage(this.players.get(turn).atackFinalAction());
        }

        System.out.println(turn + " uso ataque Final " + this.players.get(turn).getPersonaje().getEstamina());

    }
    //Activa la curacion

    public void heal(boolean whoPlayer) {
        int turn = 0;
        if (!whoPlayer) {
            turn = 1;
        }
        if (this.players.get(turn).stamiteIsNotEmpty(20)) {
            this.players.get(turn).Heal();
        }
        System.out.println(turn + " uso heal " + this.players.get(turn).getPersonaje().getEstamina());

    }

    //activa la defensa
    public void protect(boolean whoPlayer) {
        int turn = 0;
        if (!whoPlayer) {
            turn = 1;
        }
        if (this.players.get(turn).stamiteIsNotEmpty(75)) {
            if (!this.players.get(turn).defenseActive) {
                this.players.get(turn).defenseOn();
            }
        }
        System.out.println(turn + " uso protect " + this.players.get(turn).getPersonaje().getEstamina());
    }
    //resetea los labels de vida   

    public Label[] resetLabelLife(Label[] labelsLife) {

        labelsLife[0] = formatLabel(labelsLife[0], this.players.get(0).getPersonaje().getVida(), this.players.get(0).getLife());
        labelsLife[1] = formatLabel(labelsLife[1], this.players.get(1).getPersonaje().getVida(), this.players.get(1).getLife());

        return labelsLife;

    }
//resetea los labels de estamita

    public Label[] resetLabelStamite(Label[] labelsStamite) {

        labelsStamite[0] = formatLabel(labelsStamite[0], this.players.get(0).getPersonaje().getEstamina(), this.players.get(0).getStamite());
        labelsStamite[1] = formatLabel(labelsStamite[1], this.players.get(1).getPersonaje().getEstamina(), this.players.get(1).getStamite());

        return labelsStamite;
    }
//da foramto a los labels

    private Label formatLabel(Label label, int parameterVar, int parameterConst) {
        label.setText(parameterVar + "/" + parameterConst);
        if ((parameterVar) <= ((parameterConst * 20) / 100)) {
            label.setStyle("-fx-border-color:red;");
            label.setTextFill(javafx.scene.paint.Color.RED);;
        } else if ((parameterVar) <= ((parameterConst * 50) / 100)) {
            label.setStyle("-fx-border-color:orange;");
            label.setTextFill(javafx.scene.paint.Color.ORANGE);
        } else {
            label.setTextFill(javafx.scene.paint.Color.GREEN);
            label.setStyle("-fx-border-color:black;");
        }
        return label;
    }

}
