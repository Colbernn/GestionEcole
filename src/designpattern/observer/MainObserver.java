package designpattern.observer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MainObserver {
    public static void main(String[] args) {
        Classe cl1 = new Classe(1, "P00001", 6, "math", 10);
        Classe cl2 = new Classe(2, "P00002", 5, "francais", 100);
        Enseignant e1= new Enseignant(1,"E56","Chevalier","Steph","BXL",45,new BigDecimal(1500), LocalDate.now());
        Enseignant e2= new Enseignant(2,"E23","Dupont","Annie","BXL",23,new BigDecimal(1600),LocalDate.now());
        cl1.addObserver(e1);
        cl1.addObserver(e2);
        cl2.addObserver(e1);
        cl1.setNbreEleves(210);
        cl2.setNbreEleves(12);
    }
}
