package metier;

public class Cours {
    protected int idCours;
    protected String code;
    protected String intitule;


    public Cours(int id, String code, String intitule){
        this.idCours=id;
        this.code=code;
        this.intitule=intitule;
    }

    public String getCode(){
        return this.code;
    }

    public String getIntitule(){
        return this.intitule;
    }

}
