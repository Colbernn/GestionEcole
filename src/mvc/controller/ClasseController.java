package mvc.controller;

import metier.Classe;
import metier.Cours;
import mvc.model.DAOClasse;
import mvc.vue.ClasseAbstractView;

import java.util.List;

public class ClasseController {
    private DAOClasse model;
    private ClasseAbstractView view;

    public ClasseController(DAOClasse model, ClasseAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }
    public List<Classe> getAll(){
        return model.getClasses();
    }
    public Classe addClasse(Classe classe) {
        return  model.addClasse(classe);
    }

    public boolean removeClasse(Classe classe) {
        return model.removeClasse(classe);
    }

    public Classe updateClasse(Classe classe) {
        return model.updateClasse(classe);
    }

    public Classe search(int idClasse) {
        return model.readClasse(idClasse);
    }

    public List<Cours> commandes(Classe classe) {
        return  model.commandes(classe);
    }

    public List<Cours> factNonPayees(Classe classe) {
        return  model.factNonPayees(classe);
    }

    public List<Cours> factRetard(Classe classe) {
        return  model.factRetard(classe);
    }

    public List<Cours> factPayees(Classe classe) {
        return  model.factPayees(classe);
    }

    public List<Produit> produits(Classe classe) {
        return  model.produits(classe);
    }
}
