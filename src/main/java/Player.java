
public class Player {
    private String name;
    private String lastName;
    private String id;
    private String ced;
    private int wins;
    private String usuario;

    public Player(String name, String lastName, String id, String ced,String usuario, int wins) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.ced = ced;
        this.wins = wins;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
  
}
