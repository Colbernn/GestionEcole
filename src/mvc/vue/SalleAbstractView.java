package mvc.vue;

import mvc.controller.SalleController;
import metier.Salle;

import java.util.List;

public abstract class SalleAbstractView implements mvc.observer.Observer {

    protected SalleController salleController;
    protected List<Salle> ls;

    public void  setController(SalleController salleController){
        this.salleController=salleController;
    }
    public abstract void affMsg(String msg);

    public abstract Salle selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List ls) {
        this.ls = ls;
        affList(ls);
    }

}
