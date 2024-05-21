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



}