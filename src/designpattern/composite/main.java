package designpattern.composite;

import designpattern.builder.Classe;

public class main {
    public static void main(String[] args) {
        try {
            designpattern.builder.Classe cl1 = new Classe.ClasseBuilder().
                    setIdClasse(1).
                    setSigle("A45").
                    setAnnee(6).
                    setSpecialite("Math").
                    setNbreEleves(27).build();
            System.out.println(cl1);
        } catch (Exception e) {
            System.out.println("erreur" + e);
        }
    }
}
