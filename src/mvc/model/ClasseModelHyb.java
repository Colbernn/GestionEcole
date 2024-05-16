package mvc.model;

import metier.Classe;
import metier.Cours;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClasseModelHyb extends DAOClasse {
    protected Connection dbConnect;
    public  ClasseModelHyb() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Classe addClasse(Classe classe) {
        String query1 = "insert into APIClasse(sigle,annee,specialite,nbreEleves) values(?,?,?,?)";
        String query2 = "select idClasse from APIClasse where sigle= ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,classe.getSigle());
            pstm1.setInt(2,classe.getAnnee());
            pstm1.setString(3,classe.getSpecialite());
            pstm1.setInt(4,classe.getNbreEleves());

            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setString(1,classe.getSigle());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idClasse= rs.getInt(1);
                    classe.setIdClasse(idClasse);
                    notifyObservers();
                    return classe;
                }
                else {

                    System.err.println("record introuvable");
                    return null;
                }
            }
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeClasse(Classe Classe) {
        String query = "delete from APIClasse where idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,Classe.getIdClasse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return false;
        }
    }

    @Override
    public Classe updateClasse(Classe classe) {
        String query = "update APIClasse set sigle =?,annee=?,specialite=?,nbreEleve=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,classe.getSigle());
            pstm.setInt(2,classe.getAnnee());
            pstm.setString(3,classe.getSpecialite());
            pstm.setInt(4,classe.getNbreEleves());

            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readClasse(classe.getIdClasse());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Classe readClasse(int idClasse) {
        /*
        CREATE OR REPLACE FORCE EDITIONABLE VIEW "ORA30"."APIClasseCOMFACTV2" ("IDClasse", "NOM", "PRENOM", "CP", "LOCALITE", "RUE", "NUM", "TEL", "IDCOMMANDE", "NUMFACT", "MONTANT", "ETAT", "DATECOMMANDE", "DATEFACTURATION", "DATEPAYEMENT") AS
  SELECT
 apiClasse.idClasse as idClasse,
 apiClasse.nom as nom,
 apiClasse.prenom as prenom,
 apiClasse.cp as cp,
 apiClasse.localite as localite,
 apiClasse.rue as rue,
 apiClasse.num as num,
 apiClasse.tel as tel,
 apicomfact.idcommande as idcommande,
 apicomfact.numfact as numfact,
 apicomfact.montant as montant,
 apicomfact.etat as etat,
 apicomfact.datecommande as datecommande,
 apicomfact.datefacturation as datefacturation,
 apicomfact.datepayement as datepayement
FROM
apiClasse left join apicomfact on apiClasse.idClasse = apicomfact.idClasse
order by apiClasse.idClasse
;

 */

        String query = "select * from APIClasseCOMFACTV2 where idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idClasse);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                int cp = rs.getInt(4);
                String loc = rs.getString(5);
                String rue= rs.getString(6);
                String num = rs.getString(7);
                String tel = rs.getString(8);
                Classe cl = new Classe(idClasse,nom,prenom,cp,loc,rue,num,tel);
                List<ComFact> lc = new ArrayList<>();
                int numcommande = rs.getInt(9);
                if(numcommande!=0){
                    do {
                        numcommande = rs.getInt(9);
                        Integer numfact = rs.getInt(10);
                        BigDecimal montant = rs.getBigDecimal(11);
                        char etat = rs.getString(12).charAt(0);
                        LocalDate dateCom = rs.getDate(13).toLocalDate();
                        Date date = rs.getDate(14);
                        LocalDate datefact = date!=null?date.toLocalDate():null;
                        date =rs.getDate(15);
                        LocalDate datepay = date!=null?date.toLocalDate():null;
                        ComFact cf = new ComFact(numcommande, numfact, dateCom, etat, montant, cl);
                        cf.setDateFacturation(datefact);
                        cf.setDatePayement(datepay);
                        lc.add(cf);
                    }while(rs.next());
                }
                cl.setComFacts(lc);
                return cl;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            // System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List<Classe> getClasses() {
        List<Classe> lc = new ArrayList<>();
        String query="select * from APIClasse";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idClasse = rs.getInt(1);
                String sigle = rs.getString(2);
                int annee = rs.getInt(4);
                String specialite = rs.getString(5);
                int nbreEleves = rs.getInt(4);
                Classe cl = new Classe(idClasse,sigle,annee,specialite,nbreEleves);
                lc.add(cl);
            }
            return lc;
        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);

            return null;
        }
    }
    @Override
    public List<ComFact>  factPayees(Classe Classe) {
        return Classe.factPayees();
    }
    @Override
    public List<ComFact> factRetard(Classe Classe) {
        return Classe.factRetard();
    }

    @Override
    public List<ComFact> factNonPayees(Classe Classe) {
        return Classe.factNonPayees();
    }


    @Override
    public List<ComFact> commandes(Classe classe) {
        return Classe.getComFacts();
    }

    @Override
    public List<Cours> cours(Classe classe) {
        List<Cours> lco = new ArrayList<>();
        String query="select * from prodcli where idClasse = ? order by numprod";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,classe.getIdClasse());
            ResultSet rs = pstm.executeQuery();
            boolean trouve= false;
            while(rs.next()){
                trouve=true;
                int idprod = rs.getInt(2);
                String numprod = rs.getString(3);
                String descr = rs.getString(4);
                Produit pr = new Produit(idprod,numprod,descr,new BigDecimal(0),0,0);
                lpr.add(pr);
            }
            if(!trouve) System.out.println("aucune commande trouvée");
        } catch (SQLException e) {

            return null;
        }
        return lco;
    }

    @Override
    public List getNotification() {
        return getClasses();
    }
}