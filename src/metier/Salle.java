package metier;

import java.util.ArrayList;
import java.util.List;

public class Salle {
    /**
     * identifiant unique-id de la salle
     */
    protected int idSalle;
    /**
     * sigle de la salle
     */
    protected String sigle;
    /**
     * capacité de la salle
     */
    protected int capacite;
    /**
     * liste des classes de la salle
     */

    protected List<Classe> classe = new ArrayList<Classe>();
    /**
     * le constructeur parametré avec la salle
     */
    public Salle (int id, String sigle, int cap){
        this.idSalle=id;
        this.sigle=sigle;
        this.capacite=cap;
    }
    /**
     * getter sigle de la salle
     * @return sigle de la salle
     */
    public String getSigle(){
        return this.sigle;
    }
    /**
     * getter id de la salle
     * @return id de la salle
     */
    public int getIdSalle() {
        return idSalle;
    }
    /**
     * getter liste de classes de la salle
     * @return liste de classes de la salle
     */
    public List<Classe> getClasse() {
        return classe;
    }

    /**
     * getter sigle de la salle
     * @return sigle de la salle
     */
    public int getCapacite(){
        return this.capacite;
    }
    /**
     * setter id de la salle
     * @param idSalle id de la salle
     */
    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }
    /**
     * setter sigle de la salle
     * @param sigle sigle de la salle
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }
    /**
     * setter capacite de la salle
     * @param capacite capacite de la salle
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
