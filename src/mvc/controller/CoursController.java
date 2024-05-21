package mvc.controller;

import metier.Cours;
import mvc.model.DAOCours;
import mvc.vue.CoursAbstractView;

import java.util.List;

public class CoursController {
    private DAOCours model;
    private CoursAbstractView view;

    public CoursController(DAOCours model, CoursAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Cours> getAll(){
        return model.getCours();
    }
    public Cours addCours(Cours Cours) {
        return  model.addCours(Cours);
    }

    public boolean removeCours(Cours sa) {
        return model.removeCours(sa);
    }

    public Cours update(Cours Cours) {
        return model.updateCours(Cours);

    }

    public Cours search(int idCours) {
        return  model.readCours(idCours);
    }

}
