package metier;

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

    protected List<Infos> infos = new ArrayList<>();

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
        int tot=0;
        for(Infos inf : infos) {
            tot=+inf.getHeure();
        }
        return tot;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public List<Infos> getInfos() {
        return infos;
    }

    public Salle getSalle() {
        return salle;
    }

    public List<EnseignantsEtHeures> listeEnseignantsEtHeures(){
        List<EnseignantsEtHeures> enseignants = new ArrayList<>();
        for(Infos inf : infos){
            enseignants.add(new EnseignantsEtHeures(inf.getEns(), inf.getHeure()));
        }

        return enseignants;
    }

    public List<SallesEtHeures> listeSallesEtHeures(){
        List<SallesEtHeures> salles = new ArrayList<>();
        for(Infos inf : infos) {
            salles.add(new SallesEtHeures(inf.getSalle(), inf.getHeure()));
        }

        return salles;
    }

    public List<CoursEtHeures> listeCoursEtHeures(){
            List<CoursEtHeures> cours = new ArrayList<>();
            for(Infos inf : infos) {
                cours.add(new CoursEtHeures(inf.getCours(), inf.getHeure()));
            }
            return cours;
    }

    public boolean salleCapaciteOK(Salle salle){
        if(this.nbreEleves<=salle.capacite){
            return true;
        }else return false;
    }

    public void addCours (Cours cours, int heure){
        Infos newInfo=new Infos(heure, cours);
        this.infos.add(newInfo);
    }

    public void modifCours(Cours cours, Enseignant enseignant){
        for(Infos inf : infos){
            if(inf.cours==cours){
                inf.enseignant=enseignant;
            }
        }
    }

    public void modifCours(Cours cours, Salle salle){
        for(Infos inf : infos){
            if(inf.cours==cours){
                inf.salle=salle;
            }
        }
    }

    public void modifCours(Cours cours, int heure){
        for(Infos inf : infos){
            if(inf.cours==cours){
                inf.nbreHeures=heure;
            }
        }
    }

    public void suppCours(Cours cours){
        for(Infos inf : infos){
            if(inf.cours==cours){
                infos.remove(inf);
            }
        }

    }

}
