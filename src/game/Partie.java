/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static game.Profil.xmlDateToProfileDate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author josias
 */
public class Partie {
    private String date;
    private String mot;
    private int niveau;
    private int trouvé;
    private int temps;

    public Partie(String date, String mot, int niveau) {
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
        
    }

    Partie(Element partieElt) {
        //on reucpere la date
        this.date = xmlDateToProfileDate(partieElt.getAttribute("date"));
        
        // On recupere le mot
        NodeList motsPartie = partieElt.getElementsByTagName("tux:mot");
        Element motPartie = (Element) motsPartie.item(0);     
        this.mot = motPartie.getTextContent();
        
        //on recupere le niveau 
        this.niveau = Integer.parseInt(motPartie.getAttribute("niveau"));
        
        //on recupere le temps
        NodeList tempsPartie = partieElt.getElementsByTagName("temps");
        Element temps = (Element) tempsPartie.item(0);
        if(temps != null){
            try {
                this.temps = Integer.parseInt(temps.getTextContent());
            }catch (NumberFormatException e){
                System.out.println("Temps introuvable ! "); 
            }
        }
        
    }
    
    
    public Element getPartie(Document doc){
        //on cree une nouvelle partie
       Element partie = doc.createElement("partie");
       
       //on y ajoute la date
       partie.setAttribute("date", date);
       
       //on lui ajoute trouvé mais en string
       partie.setAttribute("trouvé", ""+trouvé+"%s");
       
       //on cree le temps
       Element tmp = doc.createElement("temps");   
       tmp.setTextContent(""+this.temps/1000 +" sec");
       
       //on crée le mot       
       Element motIntermediaire = doc.createElement("mot");
       motIntermediaire.setAttribute("niveau", ""+this.niveau);
       motIntermediaire.setTextContent(this.mot);
       
       
       
       //on l ajoute à partie comme fils
       partie.appendChild(tmp);
       partie.appendChild(motIntermediaire);      
        
        
        return partie;
    }

    public void setTrouve(int nbLettresRestantes) {
        this.trouvé = 100*(mot.length() - nbLettresRestantes)/mot.length();
    }
    

    public void setTemps(int temps) {
        this.temps = temps;
    }
    
    public int getNiveau() {
        return niveau;
    }
    
    
    @Override
    public String toString(){
        String str = "";
        str += "Partie date : "+date+" mot = "+mot+" niveau = "+getNiveau();
        return str;
    }

    public String getMot(){
        return mot;
    }

    
}
