package mvc.model;

import metier.Enseignant;

import java.util.List;

public abstract class DAOEnseignant extends mvc.observer.Subject {
    public abstract Enseignant addEnseignant(Enseignant enseignant);

    public abstract boolean removeEnseignant(Enseignant enseignant);

    public abstract Enseignant updateEnseignant(Enseignant enseignant);

    public abstract Enseignant readEnseignant(int idEnseignant);

    public abstract List<Enseignant> getEnseignants();
}
