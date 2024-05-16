package mvc.model;

import metier.Classe;

import java.util.List;

public abstract class DAOClasse extends mvc.observer.Subject {
    public abstract Classe addClasse(Classe classe);

    public abstract boolean removeClasse(Classe classe);

    public abstract Classe updateClasse(Classe classe);

    public abstract Classe readClasse(int idClasse);

    public abstract List<Classe> getClasses();

    public abstract List<ComFact> factPayees(Classe classe);

    public abstract List<ComFact> factRetard(Classe classe);

    public abstract List<ComFact> factNonPayees(Classe classe);


    public abstract List<ComFact> commandes(Classe classe);

    public abstract List<Produit> produits(Classe classe);
}