package mvc.vue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import metier.Classe;
import metier.Salle;
import mvc.controller.SalleController;
import utilitaires.Utilitaire;

import static utilitaires.Utilitaire.*;

public class SalleViewConsole extends SalleAbstractView {
    private Scanner sc = new Scanner(System.in);


    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }


    public void menu(){
        update(salleController.getAll());
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "capacité max de l'établissement", "fin"));

            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : capaciteMax();
                    break;
                case 6 : return;
            }
        }while(true);
    }

    private void special(Salle sa) {

        do {
            int ch = choixListe(Arrays.asList("Voir les classes avec cette salle par défaut", "voir s'il y a plus une salle plus grande", "menu principal"));
            if(ch==3) return;
            switch (ch) {
                case 1:
                    classeSalleDefaut(sa);
                    break;
                case 2:
                    voirSallePlusGrande(sa);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    private void capaciteMax(){
        List<Salle> lsa = salleController.getAll();
        int capaciteMax=0;
        for(Salle s : lsa){
            capaciteMax+=s.getCapacite();
        }
        System.out.println("la capacité totale de l'établissement est de : " + capaciteMax);
    }


    private void modifier() {
        int nl = choixElt(ls);

        Salle sa = ls.get(nl-1);
        String sigle= modifyIfNotBlank("sigle de salle",sa.getSigle());
        int capacite = Integer.parseInt(modifyIfNotBlank("capacité",""+sa.getCapacite()));
        Salle samaj =  salleController.update(new Salle(sa.getIdSalle(),sigle,capacite));
        if(samaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+samaj);
    }

    private void rechercher() {
        System.out.println("idSalle : ");
        int idSalle = sc.nextInt();
        Salle sa = salleController.search(idSalle);
        if(sa==null){
            System.out.println("Pas de salle a cette id");
        }else {
            affMsg(sa.toString());
            special(sa);
        }
    }

    private void retirer() {

        int nl = choixElt(ls);
        Salle sa = ls.get(nl-1);
        boolean ok = salleController.removeSalle(sa);
        if(ok) affMsg("salle effacé");
        else affMsg("salle non effacé");
    }

    private void ajouter() {
        System.out.print("sigle :");
        String sigle = sc.nextLine();
        System.out.print("capacite :");
        Integer capacite = sc.nextInt();
        Salle sa = salleController.addSalle(new Salle(0,sigle,capacite)) ;
        if(sa!=null) affMsg("création de :"+sa);
        else affMsg("erreur de création");
    }

    @Override
    public Salle selectionner(){
        update(salleController.getAll());
        int nl =  choixListe(ls);
        Salle sa = ls.get(nl-1);
        return sa;
    }

    private void classeSalleDefaut(Salle sa){
        System.out.println("Voici les classes qui ont choisi cette salle par défaut :");
        for(Classe cl : sa.getClasse()){
            System.out.println(cl.toString());
            System.out.println("\n");
        }
    }

    private void voirSallePlusGrande(Salle sa){
        List<Salle> lsa = salleController.getAll();
        int flag=0;
        List<Salle> lsag=new ArrayList<>();
        for(Salle s : lsa){
            if(s.getCapacite()>sa.getCapacite()){
                flag = 1;
                lsag.add(s);
            }
        }
        if(flag==1){
            System.out.println("Voici la liste des salles qui sont plus grande que celle sélectionner");
            for(Salle s : lsag){
                System.out.println(s.toString());
                System.out.println("\n");
            }
        }else{
            System.out.println("il s'agit actuellement de la salle la plus grande");
        }
    }
}
