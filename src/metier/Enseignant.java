package metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Enseignant {
    protected int idEns;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String tel;
    protected int chargeSem;
    protected BigDecimal salaireMensu;
    protected LocalDate dateEngag;

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
    public String getMatricule(){
        return this.matricule;
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }
    public String getTel(){
        return this.tel;
    }

    public int getChargeSem(){
        return this.chargeSem;
    }

    public LocalDate getDateEngag() {
        return dateEngag;
    }



}
