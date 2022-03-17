/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import env3d.advanced.EnvNode;

/**
 *
 * @author josias
 */
public class Letter extends EnvNode{
    private char lettre;

    public Letter(char l, double x, double y) {
        this.lettre = l;
        setX(x);
         setScale(4.0);
        setY(getScale()*1.1);
        setZ(y);
        
        
        String caract;
        if(l == ' '){
            caract = "cube.png";
        }else{
            caract = l+".png";
        }
        
        setTexture("models/letter/"+caract);
        setModel("models/letter/cube.obj");
        
        
    }
    
    
    
}
