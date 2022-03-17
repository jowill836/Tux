/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author josias
 */
public class LanceurDeJeu {
    public static void main(String args[]) throws SAXException, IOException{
        //Declare un jeu
        Jeu jeu;
        jeu = new JeuDevineLeMotOrdre();
        jeu.execute();
    }
    
    
}
