package metier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Classe {
    protected int idClasse;
    protected String sigle;
    protected int annee;
    protected String specialite;
    protected int nbreEleves;

    protected List<Infos> lignes = new ArrayList<>();

    protected Salle salle;

    public Classe(int id, String sigle, int annee, String specialite, int nbreEleves){
        this.idClasse=id;
        this.sigle=sigle;
        this.annee= annee;
        this.specialite=specialite;
        this.nbreEleves=nbreEleves;
        this.salle=null;
    }

    public Classe(int id, String sigle, int annee, String specialite, int nbreEleves, Salle salle){
        this.idClasse=id;
        this.sigle=sigle;
        this.annee= annee;
        this.specialite=specialite;
        this.nbreEleves=nbreEleves;
        this.salle=salle;
    }

    public int getAnnee(){
        return this.annee;
    }
    public String getSigle(){
        return this.sigle;
    }

    public String getSpecialite(){
        return this.specialite;
    }

    public int getNbreEleves(){
        return this.nbreEleves;
    }

    public int nbreHeuresTot(){

    }

    public List<Enseignant> listeEnseignantsEtHeures(){

    }

    public List<Salle> listeSallesEtHeures(){

    }

    public List<CoursEtHeure> listeCoursEtHeures(){

    }

    public boolean salleCapaciteOK(Salle salle){
        if(this.nbreEleves<=salle.capacite){
            return true;
        }else return false;
    }

    public void addCours (Cours cours, LocalDate heure){

    }

    public void modifCours(Cours cours, Enseignant enseignant){

    }

    public void modifCours(Cours cours, Salle salle){

    }

    public void modifCours(Cours cours, LocalDate heure){

    }

    public void suppCours(Cours cours){

    }

}
