/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leopierre2584;

import static java.lang.Math.abs;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author daudi
 */
public class IALooser{
    static Grille lastgrille;
    static int direction;
    public static int movechoice(Grille g){
        
        if (lastgrille!=null){
            if (lastgrille==g){
                if ((abs(direction))==1){
                    direction = 2;
                }else{
                    direction = 1;
                }
            }
        }
        if (direction==0){
            direction=1;
        }
        direction=direction*-1;
        lastgrille = g;
        int n = ThreadLocalRandom.current().nextInt(1, 5);  
        //direction entre 1 et 4
        System.out.println("Choix direction IA random: "+direction);//débuggage
        return direction;       
    }
   
}
