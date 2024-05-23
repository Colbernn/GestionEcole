package mvc.controller;

import metier.Classe;
import metier.Cours;
import metier.Enseignant;
import metier.Salle;
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

    public boolean addCours(Classe cl,Cours co, int nh){
        return  model.addCours(cl,co, nh);
    }

    public boolean modifCours1(Classe cl,Cours co, int nh){
        return model.updateCours1(cl,co, nh);
    }

    public boolean modifCours2(Classe cl,Cours co, Enseignant en){
        return model.updateCours2(cl,co, en);
    }

    public boolean modifCours3(Classe cl,Cours co, Salle sa){
        return model.updateCours3(cl,co, sa);
    }

    public boolean supCours(Classe cl,Cours co){

        return  model.removeCours(cl,co);
    }
}
