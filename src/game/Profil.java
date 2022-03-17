package game;

import game.Partie;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Profil {
    private String nom;
    private String dateNaissance;
    private String avatar;
    private ArrayList<Partie> parties;
    
    Profil(String nom, String dateNaissance){
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public Document doc;

    // Cree un DOM à partir d'un fichier XML
    Profil(String nomFichier) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        
        doc = fromXML(nomFichier);
        // parsing à compléter
        NodeList noms = doc.getElementsByTagName("tux:nom");
        Element nomJoueur = (Element)noms.item(0);
        this.nom = nomJoueur.getTextContent();
        
        NodeList dateAnnivs = doc.getElementsByTagName("tux:anniversaire");
        Element anniversaire = (Element) dateAnnivs.item(0);
        this.dateNaissance = xmlDateToProfileDate(anniversaire.getTextContent());
        
        NodeList nodeParties;
        nodeParties = doc.getElementsByTagName("tux:partie");
        parties = new ArrayList<Partie>();
        
        for(int i = 0; i < nodeParties.getLength(); i++){
            Element elementPartie = (Element) nodeParties.item(i);
            Partie partieEnCours = new Partie(elementPartie);
            
            ajouterPartie(partieEnCours);
        }     
        
        
    }

    // Cree un DOM à partir d'un fichier XML
    public Document fromXML(String nomFichier) {
        try {
            return XMLUtil.DocumentFactory.fromFile(nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Sauvegarde un DOM en XML
    public void toXML(String nomFichier) {
        try {
            XMLUtil.DocumentTransform.writeDoc(doc, nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// Takes a date in XML format (i.e. ????-??-??) and returns a date
    /// in profile format: dd/mm/yyyy
    public static String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));

        return date;
    }

    /// Takes a date in profile format: dd/mm/yyyy and returns a date
    /// in XML format (i.e. ????-??-??)
    public static String profileDateToXmlDate(String profileDate) {
        String date;
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));

        return date;
    }
    
    public void ajouterPartie(Partie p){
        parties.add(p);
    }
    
    public int getDernierNiveau(){
        int taille = parties.size() - 1;
        return parties.get(taille).getNiveau();
    }
    
    public void sauvegarder(String nomFichier){
        
    }

    public String getNom() {
        return nom;
    }

    

    

    

} 