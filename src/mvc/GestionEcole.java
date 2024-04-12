package mvc;

import mvc.controller.EnseignantController;
import mvc.controller.SalleController;
import mvc.model.DAOEnseignant;
import mvc.model.DAOSalle;
import mvc.model.ModelEnseignantDB;
import mvc.model.ModelSalleDB;
import mvc.vue.EnseignantAbstractView;
import mvc.vue.EnseignantViewConsole;
import mvc.vue.SalleAbstractView;
import mvc.vue.SalleViewConsole;
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
    public void gestion(){
        sm=new ModelSalleDB();
        sv =  new SalleViewConsole();
        sc= new SalleController(sm,sv);
        sm.addObserver(sv);
        em=new ModelEnseignantDB();
        ev =  new EnseignantViewConsole();
        ec= new EnseignantController(em,ev);
        em.addObserver(ev);
        List<String> loptions = Arrays.asList("Salles","Enseignant","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: sv.menu();
                    break;
                case 2: ev.menu();
                    break;
                case 3: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        GestionEcole gs = new GestionEcole();
        gs.gestion();
    }
}
