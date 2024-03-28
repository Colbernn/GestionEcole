package DAO;

import metier.Salle;
import myconnections.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class GestSalle {
    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.tous\n6.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }




    public void ajout() {

        System.out.print("sigle :");
        String sigle = sc.nextLine();
        System.out.print("capacite :");
        Integer capacite = sc.nextInt();
        String query1 = "insert into APISALLE(sigle,capacite) values(?,?)";
        String query2 = "select idsalle from APISALLE where sigle= ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,sigle);
            pstm1.setInt(2,capacite);
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,sigle);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idsalle= rs.getInt(1);
                    System.out.println("idsalle = "+idsalle);
                }
                else System.out.println("record introuvable");
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }


    public void recherche() {

        System.out.println("id de la salle recherché ");
        int idrech = sc.nextInt();
        String query = "select * from APISALLE where idsalle = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String sigle = rs.getString(2);
                int capacite = rs.getInt(3);
                Salle sa = new Salle(idrech,sigle,capacite);
                System.out.println(sa);

            }
            else System.out.println("record introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    public void modification() {
        System.out.println("id de la salle recherché ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouvelle capacité ");
        int capacite = sc.nextInt();
        String query = "update APISALLE set capacite=? where idsalle = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,capacite);
            pstm.setInt(2,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }
    public void suppression() {
        System.out.println("id de la salle recherché ");
        int idrech = sc.nextInt();
        String query = "delete from APISALLE where idsalle = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    private void tous() {
        String query="select * from APISALLE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idsalle = rs.getInt(1);
                String sigle = rs.getString(2);
                int capacite = rs.getInt(3);
                Salle sa = new Salle(idsalle,sigle,capacite);
                System.out.println(sa);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    public static void main(String[] args) {

        GestSalle g = new GestSalle();
        g.gestion();
    }

}

