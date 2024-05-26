package mvc;

import mvc.controller.ClasseController;
import mvc.controller.CoursController;
import mvc.controller.EnseignantController;
import mvc.controller.SalleController;
import mvc.model.*;
import mvc.vue.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestionEcole {
    private DAOSalle sm;
    private SalleController sc;
    private SalleAbstractView sv;
    private DAOEnseignant em;
    private EnseignantController ec;
    private EnseignantAbstractView ev;
    private DAOCours com;
    private CoursController coc;
    private CoursAbstractView cov;
    private DAOClasse clm;
    private ClasseController clc;
    private ClasseAbstractView clv;
    public void gestion(){
        sm=new ModelSalleDB();
        sv =  new SalleViewConsole();
        sc= new SalleController(sm,sv);
        sm.addObserver(sv);

        em=new ModelEnseignantDB();
        ev =  new EnseignantViewConsole();
        ec= new EnseignantController(em,ev);
        em.addObserver(ev);

        com=new ModelCoursDB();
        cov =  new CoursViewConsole();
        coc= new CoursController(com,cov);
        com.addObserver(cov);

        clm=new ClasseModelHyb();
        clv =  new ClasseViewConsole();
        clc= new ClasseController(clm,clv);
        clm.addObserver(clv);

        clv.setSalleView(sv);
        clv.setCoursView(cov);
        clv.setEnseignantView(ev);
        List<String> loptions = Arrays.asList("Salles","Enseignant","Cours","Classe","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: sv.menu();
                    break;
                case 2: ev.menu();
                    break;
                case 3: cov.menu();
                    break;
                case 4: clv.menu();
                    break;
                case 5: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        GestionEcole gs = new GestionEcole();
        gs.gestion();
    }
}
