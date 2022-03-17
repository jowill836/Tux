/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import env3d.Env;
import env3d.advanced.EnvNode;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author josias
 */
public class Tux extends EnvNode{
    Env env;
    Room room;
    
    
    public Tux(Env env, Room room) {
        this.env = env;
        this.room = room;
        
        setScale(4.0);
        setX(room.getWidth()/2);
        setY(getScale()*1.1);
        setZ(room.getDepth()/2);
        setTexture("models/tux/tux_happy.png");
        setModel("models/tux/tux.obj");
    }
    
    
    public void déplace(){
        if ((env.getKeyDown(Keyboard.KEY_Z) || env.getKeyDown(Keyboard.KEY_UP)) && !testeRoomColision(this.getX(), this.getZ())) { // Fleche 'haut' ou Z
       // Haut
       this.setRotateY(180);
       this.setZ(this.getZ() - 1.0);
       }
        if ((env.getKeyDown(Keyboard.KEY_Q) || env.getKeyDown(Keyboard.KEY_LEFT)) && !testeRoomColision(this.getX(), this.getZ())) { // Fleche 'gauche' ou Q
       // Gauche
       this.setRotateY(-90);
       this.setX(this.getX() - 1.0 );
       }
        
       // droite
    
       if ((env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT)) && !testeRoomColision(this.getX(), this.getZ())) { // Fleche 'gauche' ou Q
  
       this.setRotateY(90);
       this.setX(this.getX() + 1.0);
       }
       
       //bas
       if ((env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN)) && !testeRoomColision(this.getX(), this.getZ())) { // Fleche bas' ou s
       
       this.setRotateY(0);
       this.setZ(this.getZ() + 1.0);
       }
   
    }
    private boolean testeRoomColision(double x, double z){
        boolean collision = false;
        if(x<getScale() || x > room.getWidth()-getScale() || z < getScale() || z > room.getDepth()-getScale()){
            collision = true;
        }
        
       return collision;
    }
    
}
