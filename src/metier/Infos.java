package metier;

public class Infos {
    protected int idInfos;
    protected int nbreHeures;
    protected  Enseignant enseignant;
    protected Salle salle;

    public Infos (int id, int nbreHeures, Enseignant ens, Salle salle){
        this.idInfos=id;
        this.nbreHeures=nbreHeures;
        this.enseignant=ens;
        this.salle=salle;
    }


}
