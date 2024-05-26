package mvc.model;

import metier.Cours;
import myconnections.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelCoursDB extends DAOCours{

    protected Connection dbConnect;

    public ModelCoursDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Cours addCours(Cours cours) {
        String query1 = "insert into APICOURS(code,intitule) values(?,?)";
        String query2 = "select idCours from APICours where code = ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,cours.getCode());
            pstm1.setString(2,cours.getIntitule());
            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setString(1,cours.getCode());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idCours= rs.getInt(1);
                    cours.setIdCours(idCours);
                    notifyObservers();
                    return cours;
                }
                else {

                    System.err.println("record introuvable");
                    return null;
                }
            }
            else return null;

        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeCours(Cours cours) {
        String query = "delete from APICOURS where idCours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cours.getIdCours());
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
    public Cours updateCours(Cours cours) {
        String query = "update APICours set code=?, intitule=? where idCours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,cours.getCode());
            pstm.setString(2,cours.getIntitule());
            pstm.setInt(3,cours.getIdCours());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readCours(cours.getIdCours());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Cours readCours(int idCours) {
        String query = "select * from APICOURS where idCours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idCours);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String code = rs.getString(2);
                String intitule = rs.getString(3);
                Cours co = new Cours(idCours,code,intitule);
                return  co;

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
    public List<Cours> getCours() {
        List<Cours> ls = new ArrayList<>();
        String query="select * from APICOURS";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idCours = rs.getInt(1);
                String code = rs.getString(2);
                String intitule  = rs.getString(3);
                Cours sa = new Cours(idCours,code,intitule);
                ls.add(sa);
            }
            return ls;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List getNotification() {
        return getCours();
    }
}
