package metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Enseignant {
    /**
     * identifiant unique-id de l'enseignant
     */
    protected int idEns;
    /**
     * matricule de l'enseignant
     */
    protected String matricule;
    /**
     * nom de l'enseignant
     */
    protected String nom;
    /**
     * prenom de l'enseignant
     */
    protected String prenom;
    /**
     * numéro de téléphone de l'enseignant
     */
    protected String tel;
    /**
     * la charge de la semaine de l'enseignant
     */
    protected int chargeSem;
    /**
     * salaire mensuel de l'enseignant
     */
    protected BigDecimal salaireMensu;
    /**
     * date du recrutement de l'enseignant
     */
    protected LocalDate dateEngag;
    /**
     * le constructeur parametré
     */
    public Enseignant(int id, String mat, String nom, String prenom, String tel, int charge, BigDecimal salaire, LocalDate dateEngag){
        this.idEns=id;
        this.matricule=mat;
        this.nom=nom;
        this.prenom=prenom;
        this.tel=tel;
        this.chargeSem=charge;
        this.salaireMensu=salaire;
        this.dateEngag=dateEngag;
    }
    /**
     * getter matricule de l'enseignant
     * @return matricule de l'enseignant
     */
    public String getMatricule(){
        return this.matricule;
    }
    /**
     * getter nom de l'enseignant
     * @return nom de l'enseignant
     */
    public String getNom(){
        return this.nom;
    }
    /**
     * getter prenom de l'enseignant
     * @return prenom de l'enseignant
     */
    public String getPrenom(){
        return this.prenom;
    }
    /**
     * getter numero de téléphone de l'enseignant
     * @return numero de téléphone de l'enseignant
     */
    public String getTel(){
        return this.tel;
    }
    /**
     * getter charge de la semaine de l'enseignant
     * @return charge de la semaine de l'enseignant
     */
    public int getChargeSem(){
        return this.chargeSem;
    }
    /**
     * getter date de recrutement de l'enseignant
     * @return date de recrutement de l'enseignant
     */
    public LocalDate getDateEngag() {
        return dateEngag;
    }
    /**
     * getter id de l'enseignant
     * @return id de l'enseignant
     */
    public int getIdEns() {
        return idEns;
    }
    /**
     * getter salaire mensuel de l'enseignant
     * @return salaire mensuel de l'enseignant
     */
    public BigDecimal getSalaireMensu() {
        return salaireMensu;
    }

    /**
     * setter id de l'enseignant
     * @param idEns id de l'enseignant
     */
    public void setIdEns(int idEns) {
        this.idEns = idEns;
    }
    /**
     * setter matricule de l'enseignant
     * @param matricule matricule de l'enseignant
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    /**
     * setter nom de l'enseignant
     * @param nom nom de l'enseignant
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * setter prenom de l'enseignant
     * @param prenom prenom de l'enseignant
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    /**
     * setter numero de telephone de l'enseignant
     * @param tel numero de telephone de l'enseignant
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**
     * setter charge de la semaine de l'enseignant
     * @param chargeSem charge de la semaine de l'enseignant
     */
    public void setChargeSem(int chargeSem) {
        this.chargeSem = chargeSem;
    }
    /**
     * setter salaire mensuel de l'enseignant
     * @param salaireMensu salaire mensuel de l'enseignant
     */
    public void setSalaireMensu(BigDecimal salaireMensu) {
        this.salaireMensu = salaireMensu;
    }
    /**
     * setter date de recrutement de l'enseignant
     * @param dateEngag date de recrutement de l'enseignant
     */
    public void setDateEngag(LocalDate dateEngag) {
        this.dateEngag = dateEngag;
    }
}
