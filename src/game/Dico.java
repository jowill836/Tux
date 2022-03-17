/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author josias
 */
public class Dico {

    public ArrayList<String> getListeNiveau1() {
        return listeNiveau1;
    }

    private ArrayList<String> listeNiveau1;
    private ArrayList<String> listeNiveau2;
    private ArrayList<String> listeNiveau3;
    private ArrayList<String> listeNiveau4;
    private ArrayList<String> listeNiveau5;

    private String cheminFichierDico;

    public Dico(String cheminFichierDico) {
        this.cheminFichierDico = cheminFichierDico;

        listeNiveau1 = new ArrayList<String>();
        listeNiveau2 = new ArrayList<String>();
        listeNiveau3 = new ArrayList<String>();
        listeNiveau4 = new ArrayList<String>();
        listeNiveau5 = new ArrayList<String>();
    }

    public String getMotDepuisListeNiveaux(int niveau) {
        String str = "";
        switch (vérifieNiveau(niveau)) {
            case 1:
                str = getMotDepuisListe(listeNiveau1);
                break;
            case 2:
                str = getMotDepuisListe(listeNiveau2);
                break;
            case 3:
                str = getMotDepuisListe(listeNiveau3);
                break;
            case 4:
                str = getMotDepuisListe(listeNiveau4);
                break;
            case 5:
                str = getMotDepuisListe(listeNiveau5);
                break;

            default:
                break;
        }

        return str;
    }

    private String getMotDepuisListe(ArrayList<String> list) {
        Random rand = new Random();
        int n = rand.nextInt(list.size());

        return list.get(n);
    }

    public void ajouteMotADico(int niveau, String mot) {
        switch (vérifieNiveau(niveau)) {
            case 1:
                listeNiveau1.add(mot);
                break;

            case 2:
                listeNiveau2.add(mot);
                break;

            case 3:
                listeNiveau3.add(mot);
                break;
            case 4:
                listeNiveau4.add(mot);
                break;
            case 5:
                listeNiveau5.add(mot);
                break;

            default:
                break;

        }
    }

    private int vérifieNiveau(int niveau) {
        int niv = 1;
        if (niveau >= 1 && niveau <= 5) {
            niv = niveau;
        }
        return niv;
    }

    public String getCheminDico() {
        return this.cheminFichierDico;
    }

    public void lireDictionnaireDom(String path, String filename) throws SAXException, IOException {
        DOMParser parser = new DOMParser();
        parser.parse(path + filename);
        Document doc = parser.getDocument();
        NodeList liste;
        liste = doc.getElementsByTagName("tux:mot");

        for (int i = 0; i < liste.getLength(); i++) {
            Element mot = (Element) liste.item(i);

            int niveauDuMot = Integer.parseInt(mot.getAttribute("niveau"));

            String LettresDuMot = mot.getTextContent();
            ajouteMotADico(niveauDuMot, LettresDuMot);

        }

    }

}
