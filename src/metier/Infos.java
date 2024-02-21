package metier;

import java.util.List;

public class Infos {
    protected int idInfos;
    protected int nbreHeures;
    protected  Enseignant enseignant;
    protected Salle salle;

    protected Cours cours;

    public int getIdInfos() {
        return idInfos;
    }

    public int getHeure() {
        return nbreHeures;
    }

    public Enseignant getEns() {
        return enseignant;
    }

    public Salle getSalle() {
        return salle;
    }

    public Cours getCours() {
        return cours;
    }

    public void setIdInfos(int idInfos) {
        this.idInfos = idInfos;
    }

    public void setNbreHeures(int nbreHeures) {
        this.nbreHeures = nbreHeures;
    }

    public void setEns(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Infos (int id, int nbreHeures, Enseignant ens, Salle salle, Cours cours){
        this.idInfos=id;
        this.nbreHeures=nbreHeures;
        this.enseignant=ens;
        this.salle=salle;
        this.cours=cours;
    }

    public Infos (int nbreHeures, Cours cours){
        this.nbreHeures=nbreHeures;
        this.cours=cours;
    }


}
