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
public class JeuDevineLeMotOrdre extends Jeu{
    
    private int nbLettresRestantes;
    private Chronomètre chrono;

    public JeuDevineLeMotOrdre() throws SAXException, IOException {
        super();
    }
    

    @Override
    protected void démarrePartie(Partie partie) {
         //To change body of generated methods, choose Tools | Templates.
         chrono = new Chronomètre(30);
         chrono.start();
         nbLettresRestantes = partie.getMot().length();
         
    }

    @Override
    protected void appliqueRegles(Partie partie) {
         //To change body of generated methods, choose Tools | Templates.
         if(nbLettresRestantes == 0){
             finished = true;
         }
         if(tuxTrouveLettre() && nbLettresRestantes != 0){
             menuText.addText("Bravo !!!!  Vous avez gagner","Gagner", 100, 200);
             menuText.getText("Gagner").display();
             nbLettresRestantes -= 1;
             partie.setTrouve(nbLettresRestantes);
             getLettres().remove(0);
         }
         if(nbLettresRestantes != 0 && !chrono.remainsTime()){
             menuText.addText("Terminé, vous n'avez pas trouver toutes les lettres\n APPUYER ECHAP POUR QUITTER","Perdu", 100, 200);
             menuText.getText("Perdu").display();
             
             finished = true;
         }
    }

   // @Override
    protected void terminePartie(Partie partie) {
        //To change body of generated methods, choose Tools | Templates.
        chrono.stop();
        partie.setTemps(chrono.getSeconds());
        //suppression des lettres dans l'arraylist et sur lz terrain
        while(!getLettres().isEmpty()){
            getEnv().removeObject((getLettres().get(0)));
            getLettres().remove(0);
        }
        
    }
    
    private boolean tuxTrouveLettre(){
       boolean trouve = false;
       
       if(collision(getLettres().get(0))){
           trouve = true;
       }
       //suppression de la lettre trouvée
       //getEnv().removeObject(getLettres().get(0));
       
       return trouve;
    }
    
    public int getNbLettresRestantes(){
        return this.nbLettresRestantes;
    }
    
    private int getTemps(){
        return 0;
    }

    //@Override
    protected void terminerPartie(Partie partie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
