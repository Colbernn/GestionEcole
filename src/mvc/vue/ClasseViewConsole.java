package mvc.vue;

import metier.Classe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;
import static utilitaires.Utilitaire.choixListe;

public class ClasseViewConsole extends ClasseAbstractView {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    @Override
    public void menu() {
        update(classeController.getAll());
        do {

            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    @Override
    public void affList(List l) {
        affListe(l);
    }

    /*private void special(Classe cl) {

        do {
            int ch = choixListe(Arrays.asList("commandes en cours", "factures non payees", "factures en retard", "factures payees", "produits achetés", "menu principal"));
            if(ch==6) return;
            List l =   switch (ch) {
                case 1 ->  classeController.commandes(cl);

                case 2 ->  classeController.factNonPayees(cl);

                case 3 ->   classeController.factRetard(cl);

                case 4 ->   classeController.factPayees(cl);

                case 5  ->   classeController.produits(cl);
                default -> null;
            };
            if(l==null || l.isEmpty()) affMsg("aucun élément trouvée");
            else affList(l);
        } while (true);
    }*/



    private void modifier() {
        int nl = choixElt(lc) - 1;
        Classe classe = lc.get(nl);
        String sigle = modifyIfNotBlank("sigle", classe.getSigle());
        int annee = Integer.parseInt(modifyIfNotBlank("année", "" + classe.getAnnee()));
        String specialite = modifyIfNotBlank("spécialité", classe.getSpecialite());
        int nbreEleves = Integer.parseInt(modifyIfNotBlank("nombre d'élèves", "" + classe.getNbreEleves()));
        Classe cl =classeController.updateClasse(new Classe(classe.getIdClasse(), sigle, annee, specialite, nbreEleves));
        if(cl==null) affMsg("mise à jour infructueuse");
        else affMsg("mise à jour effectuée : "+cl);
    }

    private void rechercher() {
        System.out.println("idClasse : ");
        int idClasse = sc.nextInt();
        Classe cl = classeController.search(idClasse);
        if(cl==null) affMsg("recherche infructueuse");
        else {
            affMsg(cl.toString());
            //special(cl);
        }
    }

    private void retirer() {
        int nl = choixElt(lc)-1;
        Classe classe = lc.get(nl);
        boolean ok = classeController.removeClasse(classe);
        if(ok) affMsg("Classe effacé");
        else affMsg("Classe non effacé");
    }

    private void ajouter() {
        System.out.print("sigle : ");
        String sigle = sc.nextLine();
        System.out.print("annee: ");
        int annee = Integer.parseInt(sc.nextLine());
        System.out.print("specialite: ");
        String specialite = sc.nextLine();
        System.out.print("nombre d'élèves: ");
        int nbreEleve = Integer.parseInt(sc.nextLine());
        Classe cl = classeController.addClasse(new Classe(0, sigle, annee, specialite, nbreEleve));
        if(cl!=null) affMsg("création de :"+cl);
        else affMsg("erreur de création");
    }

    @Override
    public Classe selectionner() {
        update(classeController.getAll());
        int nl = choixListe(lc);
        Classe Classe = lc.get(nl - 1);
        return Classe;
    }
}
