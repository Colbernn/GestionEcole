package metier;

public class SallesEtHeures {
    /**
     * toutes les informations de la salle
     */
    protected Salle salle;
    /**
     * le nombre d'heure de la salle
     */
    protected int heure;
    /**
     * le constructeur parametré avec la salle
     */
    public SallesEtHeures(Salle salle, int heure){
        this.salle=salle;
        this.heure=heure;
    }
}
