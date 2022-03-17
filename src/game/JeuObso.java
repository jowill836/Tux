/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import env3d.Env;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.xml.sax.SAXException;

/**
 *
 * @author TOURE ALLASSANE
 */
public abstract class JeuObso {
    private Env env;
    private Room room;
    private Profil profil;
    private Tux tux;
    private ArrayList<Letter> lettres;
    private Dico dictionnaire;

    public ArrayList<Letter> getLettres() {
        return lettres;
    }
    
    
    public JeuObso(){
        // Crée un nouvel environnement
    env = new Env();
 
    // Instancie une Room
    room = new Room();
    
    // Intancie un tux
    tux = new Tux(env,room);
    // Règle la camera
    env.setCameraXYZ(50, 60, 175);
    env.setCameraPitch(-20);
    
    // Intancie un conteneur de lettres
    lettres = new ArrayList<Letter>();
 
    // Instancie de dictionnaire
    dictionnaire = new Dico("textures/dico.xml");
    // Désactive les contrôles par défaut
    env.setDefaultControl(false);
 
    // Instancie un profil par défaut
    profil = new Profil();
 
        
    }
    
    public void execute() throws SAXException, IOException{
        // pour l'instant, nous nous contentons d'appeler la méthode joue comme cela
        // et nous créons une partie vide, juste pour que cela fonctionne
        joue(new Partie());
         
        // Détruit l'environnement et provoque la sortie du programme 
        env.exit();
        
    }
    // retravailler
    public void joue(Partie partie) throws SAXException, IOException{
         // TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
        env.setRoom(room);
 
        // Instancie un Tux
        tux = new Tux(env,room);
        env.addObject(tux);
         
        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        // Initialisation du conteneur de lettre
        dictionnaire.lireDictionnaireDom("textures", "dico.xml");
        
        String motTire =dictionnaire.getMotDepuisListeNiveaux(2);
        separerEnLettre(motTire);
        
        //ajouter les lettres du mot sur le terrain
        for(Letter e : lettres){
            env.addObject(e);
        }
        
       /* Letter a = new Letter('a',(room.getWidth()/2) +1,(room.getWidth()/2));
        env.addObject(a);
        lettres.add(a);
        Letter b = new Letter('b',(room.getWidth()/3) +1,(room.getWidth()/3));
        env.addObject(b);
        lettres.add(b);
        Letter c = new Letter('c',(room.getWidth()/5) +1,(room.getWidth()/3));
        env.addObject(c);
        lettres.add(c);
        */
        démarrePartie(partie);
         
        // Boucle de jeu
        Boolean finished;
        finished = false;
        while (!finished) {
 
            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }
 
            // Contrôles des déplacements de Tux (gauche, droite, ...)
            tux.déplace();
            // ... (sera complété plus tard) ...
 
            // Ici, on applique les regles
            appliqueRegles(partie);
 
            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }
 
        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminerPartie(partie);
        
    }
    
    
    public void separerEnLettre(String motTire){
        for(int i = 0; i < motTire.length(); i++){
            Letter le;
            le = new Letter(motTire.charAt(i),positionLettreX(),positionLettreZ());
            lettres.add(le);
        }
    }
    
     public double positionLettreX(){
        double nombreAleatoire = 0.0;
            nombreAleatoire = Math.random()*( ( room.getWidth()-(tux.getScale()+2) ) - 1);
        return nombreAleatoire;
    }
     public double positionLettreZ(){
        double nombreAleatoire = 0.0;
            nombreAleatoire =  Math.random() *( ( room.getDepth() -(tux.getScale()+2) ) - 1);
        return nombreAleatoire;
    }
     
    
    protected abstract void démarrePartie(Partie partie);
    protected abstract void appliqueRegles(Partie partie);
    protected abstract void terminerPartie(Partie partie);
    protected double distance(Letter letter){
        return Math.sqrt( (tux.getX()-letter.getX())*(tux.getX()-letter.getX()) + (tux.getZ()-letter.getZ())*(tux.getZ()-letter.getZ())   );
    }
    protected boolean collision( Letter letter){
        return (distance(letter)==0);
    }

    
}
