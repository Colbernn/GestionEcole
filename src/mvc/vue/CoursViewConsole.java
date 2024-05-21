package mvc.vue;

import metier.Cours;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class CoursViewConsole extends CoursAbstractView {
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
        update(coursController.getAll());
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
        int nl = choixElt(lc);

        Cours co = lc.get(nl-1);
        String code= modifyIfNotBlank("code du cours",co.getCode());
        String intitule = modifyIfNotBlank("intitulé du Cours",co.getIntitule());
        Cours samaj =  coursController.update(new Cours(co.getIdCours(),code,intitule));
        if(samaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+samaj);
    }

    private void rechercher() {
        System.out.println("idCours : ");
        int idCours = sc.nextInt();
        coursController.search(idCours);
    }

    private void retirer() {

        int nl = choixElt(lc);
        Cours sa = lc.get(nl-1);
        boolean ok = coursController.removeCours(sa);
        if(ok) affMsg("Cours effacé");
        else affMsg("Cours non effacé");
    }

    private void ajouter() {
        System.out.print("code :");
        String code = sc.nextLine();
        System.out.print("intitule :");
        String intitule = sc.nextLine();
        Cours co = coursController.addCours(new Cours(0,code,intitule)) ;
        if(co!=null) affMsg("création de :"+co);
        else affMsg("erreur de création");
    }

    @Override
    public Cours selectionner(){
        update(coursController.getAll());
        int nl =  choixListe(lc);
        Cours co = lc.get(nl-1);
        return co;
    }
}
