package mvc.controller;

import mvc.model.DAOSalle;
import mvc.vue.SalleAbstractView;
import metier.Salle;

import java.util.List;

public class SalleController {
    private DAOSalle model;
    private SalleAbstractView view;

    public SalleController(DAOSalle model, SalleAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Salle> getAll(){
        return model.getSalles();
    }
    public Salle addSalle(Salle salle) {
        return  model.addSalle(salle);
    }

    public boolean removeSalle(Salle sa) {
        return model.removeSalle(sa);
    }

    public Salle update(Salle salle) {
        return model.updateSalle(salle);

    }

    public Salle search(int idSalle) {
        return  model.readSalle(idSalle);
    }

}
