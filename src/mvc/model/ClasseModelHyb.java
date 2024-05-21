package mvc.model;

import metier.*;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static utilitaires.Utilitaire.lecDate;

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
    public Classe readClasse(int idClasse){
        String query = "select * from APICLASSE where idClasse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idClasse);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String sigle = rs.getString(2);
                int annee  = rs.getInt(3);
                String specialite = rs.getString(4);
                int nbrEleve  = rs.getInt(5);
                Classe cl = new Classe(idClasse,sigle,annee,specialite,nbrEleve);
                return  cl;

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
    public List<Cours> cours(Classe classe) {
        List<Cours> lco = new ArrayList<>();
        String query="select * from APILIGNE where idClasse = ? order by idCours";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,classe.getIdClasse());
            ResultSet rs = pstm.executeQuery();
            boolean trouve= false;
            while(rs.next()){
                trouve=true;
                int idCours = rs.getInt(2);
                String code = rs.getString(3);
                String intitule = rs.getString(4);
                Cours co = new Cours(idCours,code,intitule);
                lco.add(co);
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