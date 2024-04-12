package mvc.model;

import metier.Salle;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelSalleDB extends DAOSalle{

    protected Connection dbConnect;

    public ModelSalleDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Salle addSalle(Salle salle) {
        String query1 = "insert into APISALLE(sigle,capacite) values(?,?)";
        String query2 = "select idsalle from APISALLE where sigle= ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,salle.getSigle());
            pstm1.setInt(2,salle.getCapacite());
            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setString(1,salle.getSigle());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idSalle= rs.getInt(1);
                    salle.setIdSalle(idSalle);
                    notifyObservers();
                    return salle;
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
    public boolean removeSalle(Salle salle) {
        String query = "delete from APISALLE where idsalle = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,salle.getIdSalle());
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
    public Salle updateSalle(Salle salle) {
        String query = "update APISALLE set sigle=?, capacite=? where idsalle = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,salle.getSigle());
            pstm.setInt(2,salle.getCapacite());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readSalle(salle.getIdSalle());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Salle readSalle(int idSalle) {
        String query = "select * from APISALLE where idsalle = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idSalle);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String sigle = rs.getString(2);
                int capacite = rs.getInt(3);
                Salle sa = new Salle(idSalle,sigle,capacite);
                return  sa;

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
    public List<Salle> getSalles() {
        List<Salle> ls = new ArrayList<>();
        String query="select * from APISALLE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idSalle = rs.getInt(1);
                String sigle = rs.getString(2);
                int capacite  = rs.getInt(3);
                Salle sa = new Salle(idSalle,sigle,capacite);
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
        return getSalles();
    }
}
