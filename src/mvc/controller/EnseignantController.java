package mvc.controller;

import metier.Enseignant;
import mvc.model.DAOEnseignant;
import mvc.vue.EnseignantAbstractView;

import java.util.List;

public class EnseignantController {
    private DAOEnseignant model;
    private EnseignantAbstractView view;

    public EnseignantController(DAOEnseignant model, EnseignantAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Enseignant> getAll(){
        return model.getEnseignants();
    }
    public Enseignant addEnseignant(Enseignant Enseignant) {
        return  model.addEnseignant(Enseignant);
    }

    public boolean removeEnseignant(Enseignant sa) {
        return model.removeEnseignant(sa);
    }

    public Enseignant update(Enseignant Enseignant) {
        return model.updateEnseignant(Enseignant);

    }

    public Enseignant search(int idEnseignant) {
        return  model.readEnseignant(idEnseignant);
    }

}
