package mvc.vue;

import metier.Enseignant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class EnseignantViewConsole extends EnseignantAbstractView {
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
        update(enseignantController.getAll());
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));

            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }



    private void modifier() {
        int nl = choixElt(le);

        Enseignant es = le.get(nl-1);
        String mat= modifyIfNotBlank("matricule de Enseignant",es.getMatricule());
        String nom= modifyIfNotBlank("nom de Enseignant",es.getNom());
        String prenom= modifyIfNotBlank("prenom de Enseignant",es.getPrenom());
        String tel= modifyIfNotBlank("telephone de Enseignant",es.getTel());
        int chargeSem = Integer.parseInt(modifyIfNotBlank("charge de la semaine",""+es.getChargeSem()));
        BigDecimal salaire = new BigDecimal(modifyIfNotBlank("salaire mensuel",""+es.getSalaireMensu()));
        LocalDate dateEngag = LocalDate.parse(modifyIfNotBlank("date d'engagement (format AAAA-MM-JJ)",""+es.getDateEngag()));
        Enseignant esmaj =  enseignantController.update(new Enseignant(es.getIdEns(),mat,nom,prenom, tel,chargeSem,salaire,dateEngag));
        if(esmaj==null) affMsg("mise à jour infructueuse");
        else affMsg("mise à jour effectuée : "+esmaj);
    }

    private void rechercher() {
        System.out.println("idEnseignant : ");
        int idEnseignant = sc.nextInt();
        Enseignant en = enseignantController.search(idEnseignant);
        affMsg(en.toString());
    }

    private void retirer() {

        int nl = choixElt(le);
        Enseignant sa = le.get(nl-1);
        boolean ok = enseignantController.removeEnseignant(sa);
        if(ok) affMsg("Enseignant effacé");
        else affMsg("Enseignant non effacé");
    }

    private void ajouter() {
        System.out.print("matricule :");
        String mat = sc.nextLine();
        System.out.print("nom :");
        String nom = sc.nextLine();
        System.out.print("prenom :");
        String prenom = sc.nextLine();
        System.out.print("telephone :");
        String tel = sc.nextLine();
        System.out.print("charge de la semaine :");
        Integer chargeSem = sc.nextInt();
        sc.nextLine();
        System.out.print("salaire mensuel :");
        BigDecimal salaire = sc.nextBigDecimal();
        sc.nextLine();
        System.out.print("date d'engagement (format JJ MM YYYY) :");
        LocalDate dateEngag=lecDate();
        Enseignant es = enseignantController.addEnseignant(new Enseignant(0,mat,nom,prenom,tel,chargeSem,salaire,dateEngag)) ;
        System.out.println(es);
        if(es!=null) affMsg("création de :"+es);
        else affMsg("erreur de création");
    }

    @Override
    public Enseignant selectionner(){
        update(enseignantController.getAll());
        int nl =  choixListe(le);
        Enseignant sa = le.get(nl-1);
        return sa;
    }
}
