package metier;

public class EnseignantsEtHeures {
    /**
     * toutes les informations de l'enseignant
     */
    protected Enseignant enseignant;
    /**
     * le nombre d'heure de l'enseignant
     */
    protected int heure;
    /**
     * le constructeur parametré avec la salle
     */
    public EnseignantsEtHeures(Enseignant ens, int heure){
        this.enseignant=ens;
        this.heure=heure;
    }

    @Override
    public String toString() {
        return "EnseignantsEtHeures{" +
                "enseignant=" + enseignant +
                ", heure=" + heure +
                '}';
    }
}
