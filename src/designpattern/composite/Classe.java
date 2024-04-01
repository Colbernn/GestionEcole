package designpattern.composite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Classe {
    /**
     * identifiant unique-id de la classe
     */
    protected int idClasse;
    /**
     * le nom/code de la classe
     */
    protected String sigle;
    /**
     * l'année de la classe ou son niveau
     */
    protected int annee;
    /**
     * la specialité de la classe ou son cours de prédilection
     */
    protected String specialite;
    /**
     * le nombre d'élèves dans la classe
     */
    protected int nbreEleves;
    /**
     * la liste des cours associés à leur enseignant et à leur nombre d'heure
     */
    protected List<Infos> infos = new ArrayList<>();
    /**
     * la salle de la classe
     */
    protected Salle salle;
    /**
     * le constructeur parametré sans la salle
     */
    public Classe(int id, String sigle, int annee, String specialite, int nbreEleves){
        this.idClasse=id;
        this.sigle=sigle;
        this.annee= annee;
        this.specialite=specialite;
        this.nbreEleves=nbreEleves;
        this.salle=null;
    }
    /**
     * le constructeur parametré avec la salle
     */
    public Classe(int id, String sigle, int annee, String specialite, int nbreEleves, Salle salle){
        this.idClasse=id;
        this.sigle=sigle;
        this.annee= annee;
        this.specialite=specialite;
        this.nbreEleves=nbreEleves;
        this.salle=salle;
    }
    /**
     * getter année de la classe
     * @return année de la classe
     */
    public int getAnnee(){
        return this.annee;
    }
    /**
     * getter sigle de la classe
     * @return sigle de la classe
     */
    public String getSigle(){
        return this.sigle;
    }
    /**
     * getter specialite de la classe
     * @return specialite de la classe
     */

    public String getSpecialite(){
        return this.specialite;
    }
    /**
     * getter nombre d'éleve de la classe
     * @return nombre d'éleve de la classe
     */

    public int getNbreEleves(){
        return this.nbreEleves;
    }
    /**
     * getter id de la classe
     * @return id de la classe
     */
    public int getIdClasse() {
        return idClasse;
    }
    /**
     * getter la liste des informations des cours de la classe
     * @return la liste des informations des cours de la classe
     */
    public List<Infos> getInfos() {
        return infos;
    }
    /**
     * getter salle de la classe
     * @return salle de la classe
     */
    public Salle getSalle() {
        return salle;
    }
    /**
     * setter id de la classe
     * @param idClasse id de la classe
     */
    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }
    /**
     * setter sigle de la classe
     * @param sigle sigle de la classe
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }
    /**
     * setter annee de la classe
     * @param annee annee de la classe
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    /**
     * setter specialité de la classe
     * @param specialite specialite de la classe
     */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    /**
     * setter nombre d'eleves de la classe
     * @param nbreEleves nombre d'eleves de la classe
     */
    public void setNbreEleves(int nbreEleves) {
        this.nbreEleves = nbreEleves;
    }
    /**
     * setter liste des cours de la classe
     * @param infos liste des cours de la classe
     */
    public void setInfos(List<Infos> infos) {
        this.infos = infos;
    }
    /**
     * setter salle de la classe
     * @param salle salle de la classe
     */
    public void setSalle(Salle salle) {
        this.salle = salle;
    }
    /**
     * calcul du nombre d'heure de cours total de la classe
     * @return le total de nombre d'heure
     */
    public int nbreHeuresTot(){
        int tot=0;
        for(Infos inf : infos) {
            tot=+inf.getHeure();
        }
        return tot;
    }
    /**
     * création d'une liste des enseignants et de leurs heures de cours
     * @return la liste
     */
    public List<EnseignantsEtHeures> listeEnseignantsEtHeures(){
        List<EnseignantsEtHeures> enseignants = new ArrayList<>();
        for(Infos inf : infos){
            enseignants.add(new EnseignantsEtHeures(inf.getEns(), inf.getHeure()));
        }

        return enseignants;
    }
    /**
     * création d'une liste des salles et de leurs heures de cours
     * @return la liste
     */
    public List<SallesEtHeures> listeSallesEtHeures(){
        List<SallesEtHeures> salles = new ArrayList<>();
        for(Infos inf : infos) {
            salles.add(new SallesEtHeures(inf.getSalle(), inf.getHeure()));
        }

        return salles;
    }
    /**
     * création d'une liste des cours et de leurs heures
     * @return la liste
     */
    public List<CoursEtHeures> listeCoursEtHeures(){
            List<CoursEtHeures> cours = new ArrayList<>();
            for(Infos inf : infos) {
                cours.add(new CoursEtHeures(inf.getCours(), inf.getHeure()));
            }
            return cours;
    }
    /**
     * vérification de la capacité d'une salle comparé aux nombre d'élèves de la classe
     * @param salle à ajouter
     * @return si capacité est supérieur ou égal au nombre d'éleve
     */
    public boolean salleCapaciteOK(Salle salle){
        if(this.nbreEleves<=salle.capacite){
            return true;
        }else return false;
    }
    /**
     * ajout d'un cours à la liste d'infos
     * @param cours,heure à ajouter
     */
    public void addCours (Cours cours, int heure){
        Infos newInfo=new Infos(heure, cours);
        this.infos.add(newInfo);
    }
    /**
     * modification de l'enseignant d'un cours dans la liste infos
     * @param cours,enseignant le cours a modifier et l'enseignant est l'élément à modifier
     */
    public void modifCours(Cours cours, Enseignant enseignant){
        for(Infos inf : infos){
            if(inf.cours.equals(cours)){
                inf.enseignant=enseignant;
            }
        }
    }
    /**
     * modification de la salle d'un cours dans la liste infos
     * @param cours,salle le cours a modifier et la salle est l'élément à modifier
     */
    public void modifCours(Cours cours, Salle salle){
        for(Infos inf : infos){
            if(inf.cours.equals(cours)){
                inf.salle=salle;
            }
        }
    }
    /**
     * modification du nombre d'heure d'un cours dans la liste infos
     * @param cours,heure le cours a modifier et le nombre d'heure est l'élément à modifier
     */
    public void modifCours(Cours cours, int heure){
        for(Infos inf : infos){
            if(inf.cours.equals(cours)){
                inf.nbreHeures=heure;
            }
        }
    }
    /**
     * suppression d'un cours dans la liste infos
     * @param cours le cours à supprimer
     * @return si la suppression s'est faite ou pas
     */
    public boolean suppCours(Cours cours){
        boolean suppresion=false;
        for(Infos inf : infos){
            if(inf.cours.equals(cours)){
                suppresion=infos.remove(inf);
            }
        }
        return suppresion;
    }

}
