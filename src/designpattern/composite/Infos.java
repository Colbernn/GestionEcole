package designpattern.composite;

import java.util.List;

public class Infos {
    /**
     * identifiant unique-id de l'infos
     */
    protected int idInfos;
    /**
     * nombre d'heure du cours
     */
    protected int nbreHeures;
    /**
     * enseignant lié au cours
     */
    protected  Enseignant enseignant;
    /**
     * salle lié au cours
     */
    protected Salle salle;
    /**
     * le cours de la l'infos
     */
    protected Cours cours;
    /**
     * getter id de l'infos
     * @return id de l'infos
     */
    public int getIdInfos() {
        return idInfos;
    }
    /**
     * getter nombre d'heures du cours
     * @return nombre d'heures du cours
     */
    public int getHeure() {
        return nbreHeures;
    }
    /**
     * getter enseignant du cours
     * @return enseignant du cours
     */
    public Enseignant getEns() {
        return enseignant;
    }
    /**
     * getter salle du cours
     * @return salle du cours
     */
    public Salle getSalle() {
        return salle;
    }
    /**
     * getter cours de l'infos
     * @return cours de l'infos
     */
    public Cours getCours() {
        return cours;
    }
    /**
     * setter id de l'infos
     * @param idInfos id de l'infos
     */
    public void setIdInfos(int idInfos) {
        this.idInfos = idInfos;
    }
    /**
     * setter nombre d'heures du cours
     * @param nbreHeures nombres d'heures du cours
     */
    public void setNbreHeures(int nbreHeures) {
        this.nbreHeures = nbreHeures;
    }
    /**
     * setter enseignant du cours
     * @param enseignant enseignant du cours
     */
    public void setEns(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
    /**
     * setter salle du cours
     * @param salle salle du cours
     */
    public void setSalle(Salle salle) {
        this.salle = salle;
    }
    /**
     * setter cours de l'infos
     * @param cours cours de l'infos
     */
    public void setCours(Cours cours) {
        this.cours = cours;
    }
    /**
     * le constructeur parametré
     */
    public Infos (int id, int nbreHeures, Enseignant ens, Salle salle, Cours cours){
        this.idInfos=id;
        this.nbreHeures=nbreHeures;
        this.enseignant=ens;
        this.salle=salle;
        this.cours=cours;
    }
    /**
     * le constructeur parametré avec uniquement le nombre d'heures et le cours
     */
    public Infos (int nbreHeures, Cours cours){
        this.nbreHeures=nbreHeures;
        this.cours=cours;
    }




}
