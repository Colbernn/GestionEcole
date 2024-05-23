package mvc.vue;

import metier.Classe;
import mvc.controller.ClasseController;
import mvc.observer.Observer;

import java.util.List;

public abstract class ClasseAbstractView implements Observer {
    protected ClasseController classeController;
    protected SalleAbstractView sav;
    protected EnseignantAbstractView env;
    protected CoursAbstractView cov;
    protected List<Classe> lc;

    public void  setController(ClasseController classeController){
        this.classeController=classeController;
    }

    public void setSalleView(SalleAbstractView sav){
        this.sav=sav;
    }

    public void setCoursView(CoursAbstractView cov){
        this.cov=cov;
    }

    public abstract void affMsg(String msg);

    public abstract Classe selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lc) {
        this.lc = lc;
        affList(lc);
    }
}