package mvc.vue;

import metier.Classe;
import metier.Cours;
import metier.Enseignant;
import metier.Salle;

import java.util.ArrayList;
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

    private void special(Classe cl) {

        do {
            int ch = choixListe(Arrays.asList("Ajouter un cours", "modifier le nombre d'heures d'un cours", "modifier l'enseignant d'un cours", "modifier la salle d'un cours", "supprimer un cours", "menu principal"));
            if(ch==6) return;
            List l =   switch (ch) {
                case 1:
                    ajouterCours(cl);
                    break;
                case 2:
                    modifierCours1(cl);
                    break;
                case 3:
                    modifierCours2(cl);
                    break;
                case 4:
                    modifierCours3(cl);
                    break;
                case 5:
                    supprimerCours(cl);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void ajouterCours(Classe cl){
        System.out.println("ajout d'une ligne");
        Cours co = cov.selectionner();
        System.out.print("nombre d'heures :");
        int nh = sc.nextInt();
        boolean ok = classeController.addCours(cl, co,nh);
        if(ok) affMsg("cours ajouté");
        else affMsg("erreur lors de l'ajout du produit");
    }

    private void modifierCours1(Classe cl) {
        System.out.println("modification d'une ligne");
        Cours co = cov.selectionner();
        System.out.print("nombre d'heures :");
        int nh = sc.nextInt();
        boolean ok = classeController.modifCours1(cl,co,nh);
        if(ok) affMsg("mise à jour effectuée");
        else  affMsg("mise à jour infructueuse");
    }

    private void modifierCours2(Classe cl) {
        System.out.println("modification d'une ligne");
        Cours co = cov.selectionner();
        System.out.print("Enseignant :");
        ArrayList<Enseignant> len = new ArrayList<>();
        len = getEnseignants();
        Enseignant en =
        boolean ok = classeController.modifCours1(cl,co,en);
        if(ok) affMsg("mise à jour effectuée");
        else  affMsg("mise à jour infructueuse");
    }

    private void modifierCours3(Classe cl) {
        System.out.println("modification d'une ligne");
        Cours co = cov.selectionner();
        System.out.print("Salle :");
        ArrayList<Salle> lsa = new ArrayList<>();
        lsa = getSalles();
        Salle sa =
        boolean ok = classeController.modifCours1(cl,co,sa);
        if(ok) affMsg("mise à jour effectuée");
        else  affMsg("mise à jour infructueuse");
    }

    private void supprimerCours(Classe cl) {
        System.out.println("suppression d'une ligne");
        Cours co = cov.selectionner();
        boolean ok = classeController.supCours(cl,co);
        if(ok) affMsg("ligne de produit supprimée");
        else affMsg("ligne de produit non supprimée");
    }

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
            special(cl);
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
