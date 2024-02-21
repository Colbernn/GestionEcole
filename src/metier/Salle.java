package metier;

import java.util.List;

public class Salle {
    protected int idSalle;
    protected String sigle;
    protected int capacite;

    protected List<Classe> classe;

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

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
