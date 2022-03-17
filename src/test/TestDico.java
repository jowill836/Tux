/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import game.Dico;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/**
 *
 * @author josias
 */
public class TestDico {
    public static void main(String args[]) throws SAXException, IOException {
        Dico dico = new Dico("textures/dico.xml");
        
        /*dico.ajouteMotADico(1, "coucou");
        dico.ajouteMotADico(1, "bb");
        
        dico.ajouteMotADico(1, "ALA");
        dico.ajouteMotADico(1, "AA");
        dico.ajouteMotADico(1, "uLM");
        dico.ajouteMotADico(1, "bobo");
*/
        
        dico.lireDictionnaireDom("textures/", "dico.xml");
        
        dico.getMotDepuisListeNiveaux(1);

        
        
        //dico.getMotDepuisListeNiveaux(1);
        System.out.println(dico.getMotDepuisListeNiveaux(1));
        System.out.println(dico.getMotDepuisListeNiveaux(1));
        System.out.println(dico.getMotDepuisListeNiveaux(2));
        System.out.println(dico.getMotDepuisListeNiveaux(3));
        System.out.println(dico.getMotDepuisListeNiveaux(4));
        System.out.println(dico.getMotDepuisListeNiveaux(5));
        System.out.println(dico.getMotDepuisListeNiveaux(1));
        System.out.println(dico.getMotDepuisListeNiveaux(3));
        System.out.println(dico.getMotDepuisListeNiveaux(5));
        
        
        
        
        
        
        
        
    }
    
}
