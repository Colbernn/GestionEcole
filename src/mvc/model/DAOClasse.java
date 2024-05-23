package mvc.model;

import metier.Classe;
import metier.Cours;
import metier.Enseignant;
import metier.Salle;

import java.util.List;

public abstract class DAOClasse extends mvc.observer.Subject {
    public abstract Classe addClasse(Classe classe);

    public abstract boolean removeClasse(Classe classe);

    public abstract Classe updateClasse(Classe classe);

    public abstract Classe readClasse(int idClasse);

    public abstract List<Classe> getClasses();

    public abstract List<Cours> cours(Classe classe);

    public abstract boolean addCours(Classe cl, Cours co, int nh);

    public abstract boolean updateCours1(Classe cl, Cours co, int nh);
    public abstract boolean updateCours2(Classe cl, Cours co, Enseignant en);
    public abstract boolean updateCours3(Classe cl, Cours co, Salle sa);

    public abstract boolean removeCours(Classe cl, Cours co);


}