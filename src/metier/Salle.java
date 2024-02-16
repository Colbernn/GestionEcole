package metier;

public class Salle {
    protected int idSalle;
    protected String sigle;
    protected int capacite;

    public Salle (int id, String sigle, int cap){
        this.idSalle=id;
        this.sigle=sigle;
        this.capacite=cap;
    }

    public String getSigle(){
        return this.sigle;
    }

    public int getCapacite(){
        return this.capacite;
    }
}
