package mvc.model;

import metier.Enseignant;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static utilitaires.Utilitaire.lecDate;

public class ModelEnseignantDB extends DAOEnseignant{

    protected Connection dbConnect;

    public ModelEnseignantDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Enseignant addEnseignant(Enseignant enseignant) {
        String query1 = "insert into APIENSEIGNANT(matricule,nom,prenom,tel,chargeSem,salaireMensu,dateEngag) values(?,?,?,?,?,?,?)";
        String query2 = "select idEnseignant from APIENSEIGNANT where matricule = ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,enseignant.getMatricule());
            pstm1.setString(2,enseignant.getNom());
            pstm1.setString(3,enseignant.getPrenom());
            pstm1.setString(4,enseignant.getTel());
            pstm1.setInt(5,enseignant.getChargeSem());
            pstm1.setBigDecimal(6,enseignant.getSalaireMensu());
            pstm1.setDate(7,java.sql.Date.valueOf(enseignant.getDateEngag()));
            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setString(1,enseignant.getMatricule());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idEnseignant= rs.getInt(1);
                    enseignant.setIdEns(idEnseignant);
                    notifyObservers();
                    return enseignant;
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
    public boolean removeEnseignant(Enseignant enseignant) {
        String query = "delete from APIENSEIGNANT where idEnseignant = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,enseignant.getIdEns());
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
    public Enseignant updateEnseignant(Enseignant enseignant) {
        String query = "update APIENSEIGNANT set matricule=?, nom=?, prenom=?, tel=?,chargeSem=?,salaireMensu=?,dateEngag=? where idEnseignant = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,enseignant.getMatricule());
            pstm.setString(2,enseignant.getNom());
            pstm.setString(3,enseignant.getPrenom());
            pstm.setString(4,enseignant.getTel());
            pstm.setInt(5,enseignant.getChargeSem());
            pstm.setBigDecimal(6,enseignant.getSalaireMensu());
            pstm.setDate(7,java.sql.Date.valueOf(enseignant.getDateEngag()));
            pstm.setInt(8,enseignant.getIdEns());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readEnseignant(enseignant.getIdEns());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Enseignant readEnseignant(int idEnseignant) {
        String query = "select * from APIENSEIGNANT where idEnseignant = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idEnseignant);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String mat = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                int chargeSem  = rs.getInt(6);
                BigDecimal salaire = rs.getBigDecimal(7);
                LocalDate dateEngag = rs.getDate(8).toLocalDate();
                Enseignant es = new Enseignant(idEnseignant,mat,nom,prenom,tel,chargeSem,salaire,dateEngag);
                return  es;

            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List<Enseignant> getEnseignants() {
        List<Enseignant> le = new ArrayList<>();
        String query="select * from APIENSEIGNANT";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idEnseignant = rs.getInt(1);
                String mat = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                int chargeSem  = rs.getInt(6);
                BigDecimal salaire = rs.getBigDecimal(7);
                LocalDate dateEngag = rs.getDate(8).toLocalDate();
                Enseignant es = new Enseignant(idEnseignant,mat,nom,prenom,tel,chargeSem,salaire,dateEngag);
                le.add(es);
            }
            return le;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List getNotification() {
        return getEnseignants();
    }
}
