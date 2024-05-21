package mvc.model;

import metier.Cours;

import java.util.List;

public abstract class DAOCours extends mvc.observer.Subject {
    public abstract Cours addCours(Cours cours);

    public abstract boolean removeCours(Cours cours);

    public abstract Cours updateCours(Cours cours);

    public abstract Cours readCours(int idCours);

    public abstract List<Cours> getCours();
}
