package mvc.vue;

import metier.Classe;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

import static utilitaires.Utilitaire.choixListe;

public class ClasseVueGraph extends ClasseAbstractView {



    public Classe create() {

        JTextField tfnom = new JTextField();
        JTextField tfprenom = new JTextField();
        JTextField tfcp = new JTextField();
        JTextField tfloc = new JTextField();
        JTextField tfrue = new JTextField();
        JTextField tfnum = new JTextField();
        JTextField tftel = new JTextField();
        Object[] message = {
                "nom: ", tfnom,
                "prénom:", tfprenom,
                "cp:", tfcp,
                "localité:", tfloc,
                "rue:", tfrue,
                "num:", tfnum,
                "tel", tftel
        };

        int option = JOptionPane.showConfirmDialog(null, message, "nouveau Classe", JOptionPane.DEFAULT_OPTION);
        String nom = tfnom.getText().toString();
        String prenom = tfprenom.getText().toString();
        Integer cp = Integer.parseInt(tfcp.getText().toString());
        String loc = tfloc.getText().toString();
        String rue = tfrue.getText().toString();
        String num = tfnum.getText().toString();
        String tel = tftel.getText().toString();
        Classe newcl = new Classe(0,nom, prenom, cp, loc, rue, num, tel);
        return newcl;
    }


    public void display(Classe cl) {
        displayMsg(cl.toString());
        if (!cl.getComFacts().isEmpty()) {
            String rep;
            do {
                rep = getMsg("Afficher ses commandes (o/n) ");
            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                int i=0;
                StringBuffer sb= new StringBuffer(200);
                for (ComFact cf : cl.getComFacts()) sb.append((++i)+"."+cf+"\n");
                displayMsg(sb.toString());
            }
        }
    }

    private String getMsg(String msg) {
        int option = JOptionPane.showConfirmDialog(null, msg, "question", JOptionPane.DEFAULT_OPTION);
        return ""+option;
    }

    private void displayMsg(String msg) {
        JOptionPane.showConfirmDialog(null, msg, "info", JOptionPane.DEFAULT_OPTION);
    }


    public Classe update(Classe cl) {

        do {
            String ch = getMsg("1.changement de téléphone\n2.fin");
            switch (ch) {
                case "1":
                    String ntel = getMsg("nouveau numéro de téléphone :");
                    cl.setTel(ntel);//on devrait tester que cela ne crée pas de doublons nom-prénom-tel
                    break;
                case "2":
                    return cl;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }



    public Integer read() {
        String ns = getMsg("numéro de Classe : ");
        int n = Integer.parseInt(ns);
        return n;
    }


    public void affAll(List<Classe> lc) {
        int i =0;
        StringBuffer sb= new StringBuffer(200);

        for(Classe cl:lc) sb.append((++i)+"."+cl+"\n");
        displayMsg(sb.toString());
    }


    public void affLobj(List lobj) {
        int i =0;
        for(Object o:lobj){
            displayMsg((++i)+"."+o.toString());
        }
    }

    @Override
    public void affMsg(String msg) {

    }

    @Override
    public Classe selectionner() {
        return null;
    }

    @Override

    public void menu() {
        update(ClasseController.getAll());
        do {

            int ch = choixListe(Arrays.asList("ajout", "fin"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2 :
                    return;
            }
        } while (true);
    }



    private void ajouter() {
        Classe cl = create();
        ClasseController.addClasse(cl);
    }

    @Override
    public void affList(List l) {
        displayMsg(l.toString());
    }
}
