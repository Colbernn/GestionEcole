package mvc.model;

import metier.Salle;

import java.util.List;

public abstract class DAOSalle extends mvc.observer.Subject {
    public abstract Salle addSalle(Salle salle);

    public abstract boolean removeSalle(Salle salle);

    public abstract Salle updateSalle(Salle salle);

    public abstract Salle readSalle(int idSalle);

    public abstract List<Salle> getSalles();
}
