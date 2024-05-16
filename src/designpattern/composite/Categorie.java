package designpattern.composite;

import java.util.HashSet;
import java.util.Set;

public class Categorie extends Element {
    private String nom;
    private Set<Element> elts=new HashSet<>();
    public Categorie (int id, String nom){
        super(id);
        this.nom=nom;
    }
    @Override
    public int capaciteMax() {
        int somme=0;
        for(Element sc:elts){
            somme+=sc.capaciteMax();
        }
        return somme;
    }
    public Set<Element> getElts(){
        return elts;
    }


}
