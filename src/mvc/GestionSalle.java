package mvc;

import mvc.controller.SalleController;
import mvc.model.DAOSalle;
import mvc.model.ModelSalleDB;
import mvc.vue.SalleAbstractView;
import mvc.vue.SalleViewConsole;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestionSalle {
    private DAOSalle sm;
    private SalleController sc;
    private SalleAbstractView sv;
    public void gestion(){
        sm=new ModelSalleDB();
        sv =  new SalleViewConsole();
        sc= new SalleController(sm,sv);
        sm.addObserver(sv);
        List<String> loptions = Arrays.asList("Salles","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: sv.menu();
                    break;
                case 2: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        GestionSalle gs = new GestionSalle();
        gs.gestion();
    }
}
