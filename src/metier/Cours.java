package metier;

public class Cours {
    /**
     * identifiant unique-id du cours
     */
    protected int idCours;
    /**
     * code du cours
     */
    protected String code;
    /**
     * intitule du cours
     */
    protected String intitule;

    /**
     * le constructeur parametré avec la salle
     */
    public Cours(int id, String code, String intitule){
        this.idCours=id;
        this.code=code;
        this.intitule=intitule;
    }
    /**
     * getter code du cours
     * @return code du cours
     */
    public String getCode(){
        return this.code;
    }
    /**
     * getter id du cours
     * @return id du cours
     */
    public int getIdCours() {
        return idCours;
    }

    /**
     * getter intitule du cours
     * @return intitule du cours
     */

    public String getIntitule(){
        return this.intitule;
    }
    /**
     * setter id du cours
     * @param idCours id du cours
     */
    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }
    /**
     * setter code du cours
     * @param code code du cours
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * setter intitule du cours
     * @param intitule intitule du cours
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }


}
