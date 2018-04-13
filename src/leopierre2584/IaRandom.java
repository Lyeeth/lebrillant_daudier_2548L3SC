/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leopierre2584;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author daudi
 */

public class IaRandom{
    
   public static int movechoice(){
        
        int n = ThreadLocalRandom.current().nextInt(1, 5);  
        //direction entre 1 et 4
        System.out.println("Choix direction IA random: "+n);//débuggage
         return n;       
    }
 
            
}
