package designpattern.composite;

public class CoursEtHeures {
    /**
     * toutes les informations du cours
     */
    protected Cours cours;
    /**
     * le nombre d'heure du cours
     */
    protected int heure;
    /**
     * le constructeur parametré avec la salle
     */
    public CoursEtHeures(Cours cours, int heure){
        this.cours=cours;
        this.heure=heure;
    }
}
